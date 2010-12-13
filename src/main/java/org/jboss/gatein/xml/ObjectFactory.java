/*
 *  Copyright (C) 2010 Red Hat, Inc. All rights reserved.
 *
 *  This is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this software; if not, write to the Free
 *  Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.gatein.xml;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * {@code ObjectFactory}
 *
 * Created on Nov 27, 2010, 5:32:09 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new instance of {@code ObjectFactory}
     */
    public ObjectFactory() {
        super();
    }

    /**
     * Create a new instance of {@code Properties}
     *
     * @return a new instance of {@code Properties}
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create a new instance of {@code Property}
     *
     * @return a new instance of {@code Property}
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create a new instance of {@code PropertyDatabase}
     *
     * @return a new instance of {@code PropertyDatabase}
     */
    public PropertyDatabase createPropertyDatabase() {
        return new PropertyDatabase();
    }

    /**
     * Create a new instance of {@code PropertyMapping}
     *
     * @return a new instance of {@code PropertyMapping}
     */
    public PropertyMapping createPropertyMapping() {
        return new PropertyMapping();
    }

    /**
     * Create a new instance of {@code DescText}
     *
     * @return a new instance of {@code DescText}
     */
    public DescText createDescText() {
        return new DescText();
    }

    /**
     * Create a new instance of {@code UIComponents}
     *
     * @return a new instance of {@code UIComponents}
     */
    public UIComponents createUIComponents() {
        return new UIComponents();
    }

    /**
     * Create a new instance of {@code UIComponent}
     *
     * @return a new instance of {@code UIComponent}
     */
    public UIComponent createUIComponent() {
        return new UIComponent();
    }

    /**
     * Create a new instance of {@code CompValue}
     *
     * @return a new instance of {@code CompValue}
     */
    public CompValue createCompValue() {
        return new CompValue();
    }

    /**
     * Create a new instance of {@code CompValues}
     *
     * @return a new instance of {@code CompValues}
     */
    public CompValues createCompValues() {
        return new CompValues();
    }
}
