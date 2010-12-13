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

            if (confirmPassword.matches("\\s*")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password cannot be null nor empty!", "Password connot be empty!"));
            }
            // the password must have 7 characters at least
            if (confirmPassword.length() < 7) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password length must contain 7 characters at least", "Password must be 7 chars+ !"));
            }
            String password = (String) this.registerBean.get("gatein.user.password");

            if (password == null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Please fill the password field first", "Fill the password field first"));
            }

            if (!password.equals(confirmPassword)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong input value",
                        "Password does not match!"));
            }
            // if validation passes with success

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The two values are identical and accepted!", "The two values are identical and accepted");
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
            this.registerBean.put("gatein.user.password", o);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password accepted!", "Password value accepted");
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
