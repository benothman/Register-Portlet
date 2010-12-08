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
package org.jboss.gatein.jsf.html;

import java.io.Serializable;
import javax.faces.component.html.HtmlInputText;

/**
 * {@code GateInHtmlInputText}
 *
 * Created on Nov 15, 2010, 10:35:50 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputText extends HtmlInputText implements Serializable {

    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInHtmlInputText";

    /**
     * Create a new instance of {@code GateInHtmlInputText}
     */
    public GateInHtmlInputText() {
        super();
        this.setValue(this.getLabel());
    }

    @Override
    public Object getSubmittedValue() {
        String value = (String) super.getSubmittedValue();
        String label = getLabel();
        if (value != null && value.trim().equals(label)) {
            return "";
        }

        return value;
    }

    @Override
    public Object getValue() {
        Object val = super.getValue();
        if (val == null) {
            return this.getLabel();
        }
        return val;
    }
}
