/*
 * File   : $Source: /alkacon/cvs/opencms/test/org/opencms/jsp/util/Attic/TestCmsJspContentUtilBean.java,v $
 * Date   : $Date: 2007/08/15 14:26:19 $
 * Version: $Revision: 1.3 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) 2002 - 2007 Alkacon Software GmbH (http://www.alkacon.com)
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
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.jsp.util;

import org.opencms.file.CmsObject;
import org.opencms.file.CmsProperty;
import org.opencms.file.CmsPropertyDefinition;
import org.opencms.file.CmsResource;
import org.opencms.test.OpenCmsTestCase;
import org.opencms.test.OpenCmsTestProperties;

import java.util.Map;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit tests for the <code>{@link CmsJspVfsAccessBean}</code>.<p>
 * 
 * @author Alexander Kandzior
 * 
 * @version $Revision: 1.3 $
 * 
 * @since 7.0.2
 */
public class TestCmsJspContentUtilBean extends OpenCmsTestCase {

    /**
     * Default JUnit constructor.<p>
     * 
     * @param arg0 JUnit parameters
     */
    public TestCmsJspContentUtilBean(String arg0) {

        super(arg0);
    }

    /**
     * Test suite for this test class.<p>
     * 
     * @return the test suite
     */
    public static Test suite() {

        OpenCmsTestProperties.initialize(org.opencms.test.AllTests.TEST_PROPERTIES_PATH);

        TestSuite suite = new TestSuite();
        suite.setName(TestCmsJspContentUtilBean.class.getName());

        suite.addTest(new TestCmsJspContentUtilBean("testReadResource"));
        suite.addTest(new TestCmsJspContentUtilBean("testReadProperties"));

        TestSetup wrapper = new TestSetup(suite) {

            protected void setUp() {

                setupOpenCms("simpletest", "/sites/default/");
            }

            protected void tearDown() {

                removeOpenCms();
            }
        };

        return wrapper;
    }

    /**
     * Tests for the {@link CmsJspVfsAccessBean#getReadResource()} method.<p>
     * 
     * @throws Exception if the test fails
     */
    public void testReadResource() throws Exception {

        CmsObject cms = getCmsObject();
        CmsJspVfsAccessBean bean = CmsJspVfsAccessBean.create(cms);

        Map readResource = bean.getReadResource();

        CmsResource res, dres;
        res = (CmsResource)readResource.get("/index.html");
        assertNotNull(res);
        dres = cms.readResource("/index.html");
        assertEquals(res, dres);

        res = (CmsResource)readResource.get("/idontexist.html");
        assertNull(res);
    }

    /**
     * Tests for the {@link CmsJspVfsAccessBean#getReadProperties()} method.<p>
     * 
     * @throws Exception if the test fails
     */
    public void testReadProperties() throws Exception {

        CmsObject cms = getCmsObject();
        CmsJspVfsAccessBean bean = CmsJspVfsAccessBean.create(cms);

        Map readProperties = bean.getReadProperties();
        Map props = (Map)readProperties.get("/index.html");
        assertNotNull(props);
        assertEquals("Index page", props.get(CmsPropertyDefinition.PROPERTY_TITLE));
        Map dprops = CmsProperty.toMap(cms.readPropertyObjects("/index.html", false));
        assertEquals(dprops, props);
    }
}