/*
 *  Copyright (C) 2010 Red Hat
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
package org.jboss.gatein.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 * {@code ProfileMappingBean}
 *
 * Created on Nov 26, 2010, 3:56:14 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class ProfileMappingBean implements Serializable {

    public static final String PROFILE_CONFIG_PATH = "/WEB-INF/profile-config.xml";

    



    /**
     * Create a new instance of {@code ProfileMappingBean}
     */
    public ProfileMappingBean() {
        super();
    }

    @PostConstruct
    public void init() {
        // TODO
    }
}
