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
package org.jboss.gatein.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * {@code GateInInputTextValidator}
 *
 * Created on Nov 15, 2010, 11:58:02 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInInputTextValidator implements Validator {

    /**
     * Create a new instance of {@code GateInInputTextValidator}
     */
    public GateInInputTextValidator() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        if (o != null) {
            if (!(o instanceof String)) {
                throw new IllegalArgumentException("The value must be a String");
            }
            String value = (String) o;
            if (value.matches("\\s*")) {
                throw new ValidatorException(new FacesMessage("Value is required!"));
            }

            HtmlInputText hInputText = (HtmlInputText) uic;
            String label = hInputText.getLabel();
            if (value.equalsIgnoreCase(label)) {
                throw new ValidatorException(new FacesMessage("Invalid input value!"));
            }
        }

    }
}
