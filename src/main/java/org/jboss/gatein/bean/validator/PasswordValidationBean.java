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
package org.jboss.gatein.bean.validator;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.jboss.gatein.bean.RegisterBean;

/**
 * {@code PasswordValidationBean}
 *
 * Created on Nov 25, 2010, 8:58:20 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class PasswordValidationBean implements Serializable, Validator {

    private RegisterBean registerBean;

    /**
     * Create a new instance of {@code PasswordValidationBean}
     */
    public PasswordValidationBean() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext fc, UIComponent uic, Object value) {

        if (value != null) {
            String confirmPassword = (String) value;

            //retrieve the resoure bundle of the application
            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");

            if (confirmPassword.matches("\\s*")) {
                String requiredMessage = resourceBundle.getString("gatein.password.empty");
                if (requiredMessage == null) {
                    requiredMessage = "Password connot be empty!";
                }
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, requiredMessage, requiredMessage));
            }
            // the password must have 7 characters at least
            if (confirmPassword.length() < 7) {
                String lengthMessage = resourceBundle.getString("gatein.password.length");
                if (lengthMessage == null) {
                    lengthMessage = "Password must be 7 chars+ !";
                }

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, lengthMessage, lengthMessage));
            }
            String password = (String) this.registerBean.get("gatein.user.password");

            String validationMessage = null;

            if (password == null) {
                validationMessage = resourceBundle.getString("gatein.password.fill");
                if (validationMessage == null) {
                    validationMessage = "Fill the password field first";
                }

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            if (!password.equals(confirmPassword)) {
                validationMessage = resourceBundle.getString("gatein.password.match");
                if (validationMessage == null) {
                    validationMessage = "Password does not match!";
                }
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }
            // if validation passes with success
            validationMessage = resourceBundle.getString("gatein.password.correct");
            if (validationMessage == null) {
                validationMessage = "The two values are identical and accepted";
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate2(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {

            String value = (String) o;
            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String validationMessage = null;
            if (value.length() < 7) {
                validationMessage = resourceBundle.getString("gatein.password.length");
                if (validationMessage == null) {
                    validationMessage = "Password must be 7 chars+ !";
                }
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            this.registerBean.put("gatein.user.password", o);

            validationMessage = resourceBundle.getString("gatein.password.accepted");
            if (validationMessage == null) {
                validationMessage = "Password value accepted";
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }

    /**
     * @return the registerBean
     */
    public RegisterBean getRegisterBean() {
        return registerBean;
    }

    /**
     * @param registerBean the registerBean to set
     */
    public void setRegisterBean(RegisterBean registerBean) {
        this.registerBean = registerBean;
    }
}
