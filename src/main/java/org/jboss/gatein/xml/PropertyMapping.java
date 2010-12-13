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
 * {@code PropertyMapping}
 *
 * Created on Nov 26, 2010, 11:53:28 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
@XmlRootElement
public class PropertyMapping implements Serializable {

    private PropertyDatabase database;

    /**
     * Create a new instance of {@code PropertyMapping}
     */
    public PropertyMapping() {
        super();
    }

    /**
     * @return the database
     */
    @XmlElement(name = "database", required=true)
    public PropertyDatabase getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(PropertyDatabase database) {
        this.database = database;
    }
}
