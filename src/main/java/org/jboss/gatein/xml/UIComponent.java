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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@code UIComponent}
 *
 * Created on Nov 28, 2010, 12:19:22 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
@XmlRootElement
public class UIComponent implements Serializable {

    private String name;
    private String propertyRef;
    private boolean required;
    private List<CompValue> values = new ArrayList<CompValue>();

    /**
     * Create a new instance of {@code UIComponent}
     */
    public UIComponent() {
        super();
    }

    /**
     * @return the name
     */
    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the propertyRef
     */
    @XmlElement(name = "property-ref")
    public String getPropertyRef() {
        return propertyRef;
    }

    /**
     * @param propertyRef the propertyRef to set
     */
    public void setPropertyRef(String propertyRef) {
        this.propertyRef = propertyRef;
    }

    /**
     * @return the required
     */
    @XmlElement(name = "required")
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the values
     */
    @XmlElement(name = "value")
    @XmlElementWrapper(name = "values")
    public List<CompValue> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<CompValue> values) {
        this.values = values;
    }
}
