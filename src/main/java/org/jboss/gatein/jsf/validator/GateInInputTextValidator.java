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
package org.jboss.gatein.jsf.validator;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
            // remove spaces at the begining and the end of the string value
            String value = ((String) o).trim();
            HtmlInputText inputText = (HtmlInputText) uic;

            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String validationMessage = null;

            if (value.matches("\\s*")) {
                validationMessage = resourceBundle.getString(UIInput.REQUIRED_MESSAGE_ID);
                if (validationMessage == null) {
                    validationMessage = "Value is required!";
                }

                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            String label = inputText.getLabel();
            if (value.equalsIgnoreCase(label)) {
                validationMessage = resourceBundle.getString("gatein.input.text");
                if (validationMessage == null) {
                    validationMessage = "The introduced value is not valid!";
                }

                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }
            // value accepted
            validationMessage = resourceBundle.getString("gatein.input.text.accepted");
            if (validationMessage == null) {
                validationMessage = "The introduced value is accepted";
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }
}
