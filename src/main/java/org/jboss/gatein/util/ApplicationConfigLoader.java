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
package org.jboss.gatein.util;

import java.io.InputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import org.jboss.gatein.xml.ObjectFactory;

/**
 * {@code ApplicationConfigLoader}
 *
 * Created on Nov 27, 2010, 6:16:10 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class ApplicationConfigLoader {

    private static final ApplicationConfigLoader appConfigLoader = new ApplicationConfigLoader();

    /**
     * Create a new instance of {@code ApplicationConfigLoader}
     */
    private ApplicationConfigLoader() {
        super();
    }

    /**
     * @return the singleton instance of {@code ApplicationConfigLoader}
     */
    public static ApplicationConfigLoader getInstance() {
        return appConfigLoader;
    }

    /**
     * Read and parse a XML file
     *
     * @param path the XML file path
     * @return The root element of the XML file as object
     * @throws Exception
     */
    public Object xmlToObject(String path) throws Exception {

        InputStream inputStream = null;
        ExternalContext extCtx = FacesContext.getCurrentInstance().getExternalContext();
        inputStream = extCtx.getResourceAsStream(path);
        String packageName = ObjectFactory.class.getPackage().getName();
        JAXBContext jc = JAXBContext.newInstance(packageName);
        Unmarshaller u = jc.createUnmarshaller();
        Object obj = u.unmarshal(inputStream);

        return obj;
    }

    /**
     * Print out the XML content of the given element
     *
     * @param o the element to print out
     * @throws Exception
     */
    public void printXML(Object o) throws Exception {
        if (o != null) {
            String packageName = ObjectFactory.class.getPackage().getName();
            JAXBContext jc = JAXBContext.newInstance(packageName);
            Marshaller m = jc.createMarshaller();
            m.setEventHandler(new DefaultValidationEventHandler());
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(o, System.out);
        }
    }
}
