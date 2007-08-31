/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/staticexport/CmsDefaultLinkSubstitutionHandler.java,v $
 * Date   : $Date: 2007/08/31 16:08:14 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2005 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.staticexport;

import org.opencms.file.CmsObject;
import org.opencms.file.types.CmsResourceTypeImage;
import org.opencms.main.CmsException;
import org.opencms.main.CmsLog;
import org.opencms.main.OpenCms;
import org.opencms.site.CmsSite;
import org.opencms.util.CmsStringUtil;
import org.opencms.workplace.CmsWorkplace;

import org.apache.commons.logging.Log;

/**
 * Default link substitution behavior.<p>
 *
 * @author  Alexander Kandzior 
 *
 * @version $Revision: 1.2 $ 
 * 
 * @since 7.0.2
 * 
 * @see CmsLinkManager#substituteLink(org.opencms.file.CmsObject, String, String, boolean) 
 *      for the method where this handler is used.
 */
public class CmsDefaultLinkSubstitutionHandler implements I_CmsLinkSubstitutionHandler {

    /** The log object for this class. */
    private static final Log LOG = CmsLog.getLog(CmsDefaultLinkSubstitutionHandler.class);

    /**
     * @see org.opencms.staticexport.I_CmsLinkSubstitutionHandler#substituteLink(org.opencms.file.CmsObject, java.lang.String, java.lang.String, boolean)
     */
    public String substituteLink(CmsObject cms, String link, String siteRoot, boolean forceSecure) {

        if (CmsStringUtil.isEmpty(link)) {
            // not a valid link parameter, return an empty String
            return "";
        }
        // make sure we have an absolute link        
        String absoluteLink = CmsLinkManager.getAbsoluteUri(link, cms.getRequestContext().getUri());

        String vfsName;
        String parameters;
        int pos = absoluteLink.indexOf('?');
        // check if the link has parameters, if so cut them
        if (pos >= 0) {
            vfsName = absoluteLink.substring(0, pos);
            parameters = absoluteLink.substring(pos);
        } else {
            vfsName = absoluteLink;
            parameters = null;
        }

        String resultLink = null;
        String uriBaseName = null;
        boolean useRelativeLinks = false;

        // determine the target site of the link        
        CmsSite currentSite = OpenCms.getSiteManager().getCurrentSite(cms);
        CmsSite targetSite = null;
        if (CmsStringUtil.isNotEmpty(siteRoot)) {
            targetSite = OpenCms.getSiteManager().getSiteForSiteRoot(siteRoot);
        }
        if (targetSite == null) {
            targetSite = currentSite;
        }
        String serverPrefix;
        // if the link points to another site, there needs to be a server prefix
        if (targetSite != currentSite) {
            serverPrefix = targetSite.getUrl();
        } else {
            serverPrefix = "";
        }

        // in the online project, check static export and secure settings

        if (cms.getRequestContext().currentProject().isOnlineProject()) {

            // first check if this link needs static export

            CmsStaticExportManager exportManager = OpenCms.getStaticExportManager();
            // check if we need relative links in the exported pages
            if (exportManager.relativeLinksInExport(cms.getRequestContext().getSiteRoot()
                + cms.getRequestContext().getUri())) {
                // try to get base URI from cache  
                uriBaseName = exportManager.getCachedOnlineLink(exportManager.getCacheKey(
                    cms.getRequestContext().getSiteRoot(),
                    cms.getRequestContext().getUri()));
                if (uriBaseName == null) {
                    // base not cached, check if we must export it
                    if (exportManager.isExportLink(cms, cms.getRequestContext().getUri())) {
                        // base URI must also be exported
                        uriBaseName = exportManager.getRfsName(cms, cms.getRequestContext().getUri());
                    } else {
                        // base URI dosn't need to be exported
                        uriBaseName = exportManager.getVfsPrefix() + cms.getRequestContext().getUri();
                    }
                    // cache export base URI
                    exportManager.cacheOnlineLink(exportManager.getCacheKey(
                        cms.getRequestContext().getSiteRoot(),
                        cms.getRequestContext().getUri()), uriBaseName);
                }
                // use relative links only on pages that get exported
                useRelativeLinks = uriBaseName.startsWith(OpenCms.getStaticExportManager().getRfsPrefix(
                    cms.getRequestContext().getSiteRoot() + cms.getRequestContext().getUri()));
            }

            // check if we have the absolute VFS name for the link target cached
            resultLink = exportManager.getCachedOnlineLink(cms.getRequestContext().getSiteRoot() + ":" + absoluteLink);
            if (resultLink == null) {
                String storedSiteRoot = cms.getRequestContext().getSiteRoot();
                try {
                    cms.getRequestContext().setSiteRoot(targetSite.getSiteRoot());
                    // didn't find the link in the cache
                    if (exportManager.isExportLink(cms, vfsName)) {
                        // export required, get export name for target link
                        resultLink = exportManager.getRfsName(cms, vfsName, parameters);
                        // now set the parameters to null, we do not need them anymore
                        parameters = null;
                    } else {
                        // no export required for the target link
                        resultLink = exportManager.getVfsPrefix().concat(vfsName);
                        // add cut off parameters if required
                        if (parameters != null) {
                            resultLink = resultLink.concat(parameters);
                        }
                    }
                } finally {
                    cms.getRequestContext().setSiteRoot(storedSiteRoot);
                }
                // cache the result
                exportManager.cacheOnlineLink(cms.getRequestContext().getSiteRoot() + ":" + absoluteLink, resultLink);
            }

            // now check for the secure settings 

            // check if either the current site or the target site does have a secure server configured
            if (targetSite.hasSecureServer() || currentSite.hasSecureServer()) {

                if (!vfsName.startsWith(CmsWorkplace.VFS_PATH_SYSTEM)) {
                    // don't make a secure connection to the "/system" folder (why ?)
                    int linkType = -1;
                    try {
                        // read the linked resource 
                        linkType = cms.readResource(vfsName).getTypeId();
                    } catch (CmsException e) {
                        // the resource could not be read
                        if (LOG.isInfoEnabled()) {
                            String message = Messages.get().getBundle().key(
                                Messages.LOG_RESOURCE_ACESS_ERROR_3,
                                vfsName,
                                cms.getRequestContext().currentUser().getName(),
                                cms.getRequestContext().getSiteRoot());
                            if (LOG.isDebugEnabled()) {
                                LOG.debug(message, e);
                            } else {
                                LOG.info(message);
                            }
                        }
                    }

                    // images are always referenced without a server prefix
                    if (linkType != CmsResourceTypeImage.getStaticTypeId()) {
                        // check the secure property of the link
                        boolean secureLink = exportManager.isSecureLink(cms, vfsName, targetSite.getSiteRoot());
                        boolean secureRequest = exportManager.isSecureLink(cms, cms.getRequestContext().getUri());
                        // if we are on a normal server, and the requested resource is secure, 
                        // the server name has to be prepended                        
                        if (secureLink && (forceSecure || !secureRequest)) {
                            serverPrefix = targetSite.getSecureUrl();
                        } else if (!secureLink && secureRequest) {
                            serverPrefix = targetSite.getUrl();
                        }
                    }
                }
            }
            // make absolute link relative, if relative links in export are required
            // and if the link does not point to another server
            if (useRelativeLinks && CmsStringUtil.isEmpty(serverPrefix)) {
                resultLink = CmsLinkManager.getRelativeUri(uriBaseName, resultLink);
            }

        } else {

            // offline project, no export or secure handling required
            if (OpenCms.getRunLevel() >= OpenCms.RUNLEVEL_3_SHELL_ACCESS) {
                // in unit test this code would fail otherwise
                resultLink = OpenCms.getStaticExportManager().getVfsPrefix().concat(vfsName);
            }

            // add cut off parameters and return the result
            if ((parameters != null) && (resultLink != null)) {
                resultLink = resultLink.concat(parameters);
            }

        }
        return serverPrefix.concat(resultLink);
    }
}