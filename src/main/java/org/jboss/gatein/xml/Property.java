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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@code Property}
 *
 * Created on Nov 26, 2010, 9:35:55 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
@XmlRootElement(name = "property")
public class Property implements Serializable {

    private String name;
    private String shortName;
    private String type;
    private String accessMode;
    private String usage;
    private DescText displayName;
    private DescText description;
    private PropertyMapping mapping;

    /**
     * Create a new instance of {@code Property}
     */
    public Property() {
        super();
    }

    /**
     * @return the name
     */
    @XmlElement(name = "name", required = true)
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
     * @return the shortName
     */
    @XmlElement(name = "short-name")
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the type
     */
    @XmlElement(name = "type", required = true)
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the accessMode
     */
    @XmlElement(name = "access-mode", defaultValue = "read-write")
    public String getAccessMode() {
        return accessMode;
    }

    /**
     * @param accessMode the accessMode to set
     */
    public void setAccessMode(String accessMode) {
        this.accessMode = accessMode;
    }

    /**
     * @return the usage
     */
    @XmlElement(name = "usage", defaultValue = "EN")
    public String getUsage() {
        return usage;
    }

    /**
     * @param usage the usage to set
     */
    public void setUsage(String usage) {
        this.usage = usage;
    }

    /**
     * @return the displayName
     */
    @XmlElement(name = "display-name")
    public DescText getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(DescText displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the mapping
     */
    @XmlElement(name = "mapping", required = true)
    public PropertyMapping getMapping() {
        return mapping;
    }

    /**
     * @param mapping the mapping to set
     */
    public void setMapping(PropertyMapping mapping) {
        this.mapping = mapping;
    }

    /**
     * @return the description
     */
    @XmlElement(name = "description")
    public DescText getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(DescText description) {
        this.description = description;
    }
}
