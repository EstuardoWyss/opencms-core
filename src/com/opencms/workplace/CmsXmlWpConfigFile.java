/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/workplace/Attic/CmsXmlWpConfigFile.java,v $
* Date   : $Date: 2002/10/18 16:54:03 $
* Version: $Revision: 1.43 $
*
* This library is part of OpenCms -
* the Open Source Content Mananagement System
*
* Copyright (C) 2001  The OpenCms Group
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* For further information about OpenCms, please see the
* OpenCms Website: http://www.opencms.org
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/


package com.opencms.workplace;

import com.opencms.file.*;
import com.opencms.core.*;
import com.opencms.template.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.*;
import javax.servlet.http.*;

/**
 * Definition of some paths for the workplace. Currently some are static,
 * but can be switched to read from configurations like registry.
 *
 * @author Alexander Lucas
 * @author Michael Emmerich
 * @author Andreas Schouten
 * @version $Revision: 1.43 $ $Date: 2002/10/18 16:54:03 $
 */

public class CmsXmlWpConfigFile {

    /** The cms object to get access to OpenCms */
    private CmsObject m_cms = null;

    /**
     * Default constructor.
     */

    public CmsXmlWpConfigFile() throws CmsException {
    }

    /**
     * Constructor for creating a new config file object.
     * The parameter cms is not needed at the moment.
     *
     * @param cms CmsObject object for accessing system resources.
     */
    public CmsXmlWpConfigFile(CmsObject cms) throws CmsException {
        m_cms = cms;
    }

    /**
     * Gets the path at which the folders with the download galleries are
     * @return Path for download galleries.
     */
    public String getDownGalleryPath(){
        return I_CmsWpConstants.C_GALLERYPATH_DOWNLOAD;
    }

    /**
     * Gets the path at which the folders with the html galleries are
     * @return Path for html galleries.
     */
    public String getHtmlGalleryPath(){
        return I_CmsWpConstants.C_GALLERYPATH_HTML;
    }

    /**
     * Gets the path at which the folders with the picture galleries are
     * @return Path for picture galleries.
     */
    public String getPicGalleryPath(){
        return I_CmsWpConstants.C_GALLERYPATH_PICS;
    }

    /**
     * Gets the path at which the folders with the externallink galleries are
     * @return Path for externallink galleries.
     */
    public String getLinkGalleryPath() {
        return I_CmsWpConstants.C_GALLERYPATH_EXTERNALLINKS;
    }
    
    /**
     * Gets the path for OpenCms language files.
     * @return Path for language files.
     * @exception CmsException if the corresponding XML tag doesn't exist in the workplace definition file.
     */

    public String getLanguagePath() throws CmsException {
        return "/system/workplace/config/language/";
    }

    /**
     * Gets the path for OpenCms workplace action files.
     * @return Path for OpenCms workplace action files.
     * @exception CmsException if the corresponding XML tag doesn't exist in the workplace definition file.
     */
    public String getWorkplaceActionPath() throws CmsException {
        return "/system/workplace/action/";
    }

    /**
     * Gets the path for OpenCms workplace administration files.
     * @return Path for OpenCms workplace administration files.
     * @exception CmsException if the corresponding XML tag doesn't exist in the workplace definition file.
     */
    public String getWorkplaceAdministrationPath() throws CmsException {
        return "/system/workplace/administration/";
    }

    /**
     * Gets the path for system picture files.
     * @return Path for picture files.
     * @exception CmsException if the corresponding XML tag doesn't exist in the workplace definition file.
     */
    public String getWpPicturePath() throws CmsException {
        if(new Boolean(OpenCms.getRegistry().getSystemValue("UseWpPicturesFromVFS")).booleanValue()) {
            // read the wp images from the vfs - so add the servlet url to the return value
            return m_cms.getRequestContext().getRequest().getServletUrl() + I_CmsWpConstants.C_SYSTEM_PICS_PATH;
        } else {
            // read the wp images from the webapps context
            return "/pics/system/";
        }
    }

    public void getWorkplaceIniData(Vector names, Vector values, String tag, String element) throws CmsException {
        if(tag.equals("NEWRESOURCES")) {
            names.add("folder");    values.add("explorer_files_new_folder.html");
            names.add("page");      values.add("explorer_files_new_page.html");
            // names.add("pdfpage");   values.add("explorer_files_new_pdfpage.html");
            names.add("gemadipage"); values.add("explorer_files_new_gemadipage.html");
            names.add("link");      values.add("explorer_files_new_link.html");
            names.add("othertype"); values.add("explorer_files_new_othertype.html");
            names.add("upload");    values.add("explorer_files_new_upload.html");
        } else {
            names.add("binary");    values.add("binary");
            names.add("plain");     values.add("plain");
            names.add("image");     values.add("image");
        }
    }

}
