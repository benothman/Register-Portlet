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
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * {@code ZipCodeValidator}
 *
 * Created on Nov 29, 2010, 2:11:21 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class ZipCodeValidator implements Validator {



    /**
     * Create a new instance of {@code ZipCodeValidator}
     */
    public ZipCodeValidator() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        if (o != null) {
            String value = ((String) o).trim();
            String label = ((HtmlInputText) uic).getLabel();
            if (!value.matches("\\s*") && !value.equalsIgnoreCase(label)) {

                //retrieve the resoure bundle of the application
                ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
                String validationMessage = null;

                try {
                    long zipCode = Long.parseLong(value);
                    if (zipCode < 1 || zipCode > 500000) {
                        validationMessage = resourceBundle.getString("gatein.zipcode.range");
                        if (validationMessage == null) {
                            validationMessage = "Value must be between 1 and 500000!";
                        }
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
                    }
                } catch (NumberFormatException nfe) {
                    validationMessage = resourceBundle.getString("gatein.zipcode.format");
                    if (validationMessage == null) {
                        validationMessage = "Input must be an integer!";
                    }

                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            validationMessage, validationMessage));
                }

                validationMessage = resourceBundle.getString("gatein.zipcode.accepted");
                if (validationMessage == null) {
                    validationMessage = "Value accepted!";
                }

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
                fc.addMessage(uic.getClientId(fc), message);
            }
        }
    }
}
