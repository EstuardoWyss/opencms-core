/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/file/Attic/CmsResourceTypeImage.java,v $
* Date   : $Date: 2002/10/18 16:54:59 $
* Version: $Revision: 1.4 $
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

package com.opencms.file;

import com.opencms.core.*;
import java.util.*;

/**
 * This class describes the resource-type image.
 *
 * @author
 * @version 1.0
 */

public class CmsResourceTypeImage extends CmsResourceTypePlain{

    public static final String C_TYPE_RESOURCE_NAME = "image";

    /**
     * Constructor, creates a new CmsResourceType object.
     *
     * @param resourceType The id of the resource type.
     * @param launcherType The id of the required launcher.
     * @param resourceTypeName The printable name of the resource type.
     * @param launcherClass The Java class that should be invoked by the launcher.
     * This value is <b> null </b> if the default invokation class should be used.
     * /
    public CmsResourceTypeImage(int resourceType, int launcherType,
                           String resourceTypeName, String launcherClass){

        super(resourceType, launcherType, resourceTypeName, launcherClass);
    }
*/
    public CmsResource createResource(CmsObject cms, String newResourceName, Hashtable properties, byte[] contents) throws CmsException{
        CmsResource res = cms.doCreateFile(newResourceName, contents, C_TYPE_RESOURCE_NAME, properties);
        // lock the new file
        cms.lockResource(newResourceName);
        return res;
    }
}