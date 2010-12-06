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
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.UserHandler;

/**
 * {@code UserNameValidator}
 *
 * Created on Dec 6, 2010, 10:05:03 AM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class UserNameValidator implements Validator {

    /**
     * Create a new instance of {@code UserNameValidator}
     */
    public UserNameValidator() {
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
            String value = ((String) o).trim();
            HtmlInputText inputText = (HtmlInputText) uic;
            if (value.matches("\\s*")) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is required!",
                        "The username connot be empty"));
            }

            String label = inputText.getLabel();
            if (value.equalsIgnoreCase(label)) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "The introduced value is not valid!",
                        "The value " + value + " is not accepted!"));
            }

            ExoContainer container = ExoContainerContext.getContainerByName("portal");
            OrganizationService orgService = (OrganizationService) container.getComponentInstanceOfType(OrganizationService.class);
            UserHandler userHandler = orgService.getUserHandler();


            try {
                if (userHandler.findUserByName(value) != null) {
                    throw new ValidatorException(
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exist", "The username is already used!"));
                }
            } catch (Exception exp) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while accessing database", exp.getMessage()));
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The username is accepted", "The username is accepted");
            fc.addMessage(uic.getClientId(fc), message);
        }
    }
}
