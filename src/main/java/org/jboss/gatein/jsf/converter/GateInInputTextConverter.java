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
package org.jboss.gatein.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * {@code GateInInputTextConverter}
 *
 * Created on Jan 10, 2011, 9:53:09 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInInputTextConverter implements Converter {

    /**
     * Create a new instance of {@code GateInInputTextConverter}
     */
    public GateInInputTextConverter() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter.getAsObject(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        HtmlInputText uii = (HtmlInputText) uic;
        String label = uii.getLabel();

        // if the string value is not null and it is equal to the component label
        // the returned value is the empty string, otherwise the string itself is
        // returned

        return string != null && string.trim().equalsIgnoreCase(label) ? "" : string;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter.getAsString(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        // if the object is not null
        if (o != null) {
            HtmlInputText uii = (HtmlInputText) uic;
            String label = uii.getLabel();
            String string = (String) o;

            // if the string value is empty, the label value is returned, otherwise
            // the string itself is returned
            return (string.trim().length() == 0) ? label : string;
        }

        // if the object is null, a null value is returned
        return null;
    }
}
