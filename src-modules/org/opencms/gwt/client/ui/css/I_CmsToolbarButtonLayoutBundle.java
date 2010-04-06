/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/gwt/client/ui/css/Attic/I_CmsToolbarButtonLayoutBundle.java,v $
 * Date   : $Date: 2010/04/06 06:52:32 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
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

package org.opencms.gwt.client.ui.css;

import com.google.gwt.core.client.GWT;

/**
 * Resource bundle to access CSS and image resources for tool-bar buttons.
 * 
 * @author Tobias Herrmann
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 8.0.0
 */
public interface I_CmsToolbarButtonLayoutBundle extends I_CmsImageBundle {

    /** The button CSS. */
    public interface I_CmsToolbarButtonCss extends I_CmsLayoutBundle.I_CmsStateCss {

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String optionBar();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarAdd();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarClipboard();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarEdit();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarExit();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarMove();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarNew();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarProperties();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarPublish();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarRecent();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarRemove();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarSave();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarSelection();

        /** Access method.<p>
         * 
         * @return the CSS class name
         */
        String toolbarSitemap();
    }

    /** The bundle instance. */
    I_CmsToolbarButtonLayoutBundle INSTANCE = GWT.create(I_CmsToolbarButtonLayoutBundle.class);

    /**
     * Access method.<p>
     * 
     * @return the button CSS
     */
    @Source("toolbarButton.css")
    I_CmsToolbarButtonCss toolbarButtonCss();

}