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

package org.opencms.repository;

import org.opencms.configuration.CmsConfigurationException;
import org.opencms.configuration.CmsParameterConfiguration;
import org.opencms.configuration.I_CmsConfigurationParameterHandler;

/**
 * Abstract implementation of the repository interface {@link I_CmsRepository}.<p>
 * 
 * Handles the functionality of basic configuration. This is actually the configuration
 * of param/values and the filters ({@link CmsRepositoryFilter}) to use of the repository.<p>
 * 
 * @since 6.2.4
 */
public abstract class A_CmsRepository implements I_CmsRepository, I_CmsConfigurationParameterHandler {

    /** The repository configuration. */
    private CmsParameterConfiguration m_configuration;

    /** The filter to use for the repository. */
    private CmsRepositoryFilter m_filter;

    /** The name of the repository. */
    private String m_name;

    /**
     * Default constructor initializing member variables.<p>
     */
    public A_CmsRepository() {

        m_configuration = new CmsParameterConfiguration();
        m_filter = null;
    }

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#addConfigurationParameter(java.lang.String, java.lang.String)
     */
    public void addConfigurationParameter(String paramName, String paramValue) {

        m_configuration.add(paramName, paramValue);
    }

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#getConfiguration()
     */
    public CmsParameterConfiguration getConfiguration() {

        return m_configuration;
    }

    /**
     * Returns the filter.<p>
     *
     * @return the filter
     */
    public CmsRepositoryFilter getFilter() {

        return m_filter;
    }

    /**
     * @see org.opencms.repository.I_CmsRepository#getName()
     */
    public String getName() {

        return m_name;
    }

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#initConfiguration()
     */
    public void initConfiguration() throws CmsConfigurationException {

        if (m_filter != null) {
            m_filter.initConfiguration();
        }

        // suppress the compiler warning, this is never true
        if (m_configuration == null) {
            throw new CmsConfigurationException(null);
        }

    }

    /**
     * Sets the filter.<p>
     *
     * @param filter the filter to set
     */
    public void setFilter(CmsRepositoryFilter filter) {

        m_filter = filter;
    }

    /**
     * @see org.opencms.repository.I_CmsRepository#setName(String)
     */
    public void setName(String name) {

        m_name = name;
    }

}
