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

package org.opencms.ade.sitemap.client.hoverbar;

import org.opencms.ade.sitemap.client.Messages;
import org.opencms.ade.sitemap.client.control.CmsSitemapController;
import org.opencms.ade.sitemap.shared.CmsClientSitemapEntry;
import org.opencms.gwt.client.CmsCoreProvider;
import org.opencms.gwt.client.ui.css.I_CmsImageBundle;

import com.google.gwt.user.client.Window;

/**
 * Sitemap context menu goto sub sitemap entry.<p>
 * 
 * @since 8.0.0
 */
public class CmsGotoSubSitemapMenuEntry extends A_CmsSitemapMenuEntry {

    /**
     * Constructor.<p>
     * 
     * @param hoverbar the hoverbar 
     */
    public CmsGotoSubSitemapMenuEntry(CmsSitemapHoverbar hoverbar) {

        super(hoverbar);
        setImageClass(I_CmsImageBundle.INSTANCE.contextMenuIcons().gotoSub());
        setLabel(Messages.get().key(Messages.GUI_HOVERBAR_GOTO_SUB_0));
        setActive(true);
    }

    /**
     * @see org.opencms.gwt.client.ui.contextmenu.I_CmsContextMenuEntry#execute()
     */
    public void execute() {

        String sitePath = getHoverbar().getSitePath();
        if (!sitePath.endsWith("/")) {
            sitePath += "/";
        }
        CmsSitemapController controller = getHoverbar().getController();
        String uri = CmsCoreProvider.get().getUri() + "?path=" + sitePath;
        final String returnCode = Window.Location.getParameter(CmsCoreProvider.PARAM_RETURNCODE);
        if ((returnCode != null) && (returnCode.length() != 0)) {
            uri += "&returncode=" + returnCode;
        }
        controller.leaveEditor(uri);
    }

    /**
     * @see org.opencms.ade.sitemap.client.hoverbar.A_CmsSitemapMenuEntry#onShow(org.opencms.ade.sitemap.client.hoverbar.CmsHoverbarShowEvent)
     */
    @Override
    public void onShow(CmsHoverbarShowEvent event) {

        String sitePath = getHoverbar().getSitePath();
        CmsSitemapController controller = getHoverbar().getController();
        CmsClientSitemapEntry entry = controller.getEntry(sitePath);
        setVisible((entry != null) && entry.isSubSitemapType() && !controller.isRoot(sitePath));
    }
}
