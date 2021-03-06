/*
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) Alkacon Software GmbH (http://www.alkacon.com)
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

package org.opencms.ade.galleries.client.preview;

import org.opencms.ade.galleries.shared.CmsResourceInfoBean;
import org.opencms.gwt.client.rpc.CmsRpcAction;

import java.util.Map;

/**
 * Binary preview dialog controller.<p>
 * 
 * This class handles the communication between preview dialog and the server.  
 * 
 * @since 8.0.0
 */
public final class CmsBinaryPreviewController extends A_CmsPreviewController<CmsResourceInfoBean> {

    /** The preview handler. */
    private CmsBinaryPreviewHandler m_handler;

    /**
     * Constructor.<p>
     * 
     * @param handler the preview controller handler
     * @param locale the content locale
     */
    public CmsBinaryPreviewController(CmsBinaryPreviewHandler handler, String locale) {

        m_handler = handler;
        m_locale = locale;
        m_handler.init(this);
    }

    /**
     * Constructor.<p>
     * 
     * @param locale the content locale
     */
    public CmsBinaryPreviewController(String locale) {

        m_locale = locale;
    }

    /**
     * @see org.opencms.ade.galleries.client.preview.A_CmsPreviewController#getHandler()
     */
    @Override
    public I_CmsPreviewHandler<CmsResourceInfoBean> getHandler() {

        if (m_handler == null) {
            throw new UnsupportedOperationException("Preview handler not initialized");
        }
        return m_handler;
    }

    /**
     * Loads the resource info and displays the retrieved data.<p>
     * 
     * @param resourcePath the resource path
     */
    public void loadResourceInfo(final String resourcePath) {

        CmsRpcAction<CmsResourceInfoBean> action = new CmsRpcAction<CmsResourceInfoBean>() {

            /**
             * @see org.opencms.gwt.client.rpc.CmsRpcAction#execute()
             */
            @Override
            public void execute() {

                getService().getResourceInfo(resourcePath, m_locale, this);
            }

            /**
             * @see org.opencms.gwt.client.rpc.CmsRpcAction#onResponse(java.lang.Object)
             */
            @Override
            protected void onResponse(CmsResourceInfoBean result) {

                showData(result);
            }
        };
        action.execute();
    }

    /**
     * @see org.opencms.ade.galleries.client.preview.A_CmsPreviewController#removePreview()
     */
    @Override
    public void removePreview() {

        super.removePreview();
        m_handler = null;
    }

    /**
     * @see org.opencms.ade.galleries.client.preview.I_CmsPreviewController#saveProperties(java.util.Map)
     */
    public void saveProperties(final Map<String, String> properties) {

        CmsRpcAction<CmsResourceInfoBean> action = new CmsRpcAction<CmsResourceInfoBean>() {

            /**
             * @see org.opencms.gwt.client.rpc.CmsRpcAction#execute()
             */
            @Override
            public void execute() {

                getService().updateResourceProperties(getResourcePath(), m_locale, properties, this);
            }

            /**
             * @see org.opencms.gwt.client.rpc.CmsRpcAction#onResponse(java.lang.Object)
             */
            @Override
            protected void onResponse(CmsResourceInfoBean result) {

                showData(result);
            }
        };
        action.execute();

    }
}
