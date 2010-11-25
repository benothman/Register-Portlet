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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code PasswordValidationBean}
 *
 * Created on Nov 25, 2010, 8:58:20 AM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class PasswordValidationBean implements Serializable {

    private String password;
    private String confirmPassword;
    private static final Logger logger = LoggerFactory.getLogger(PasswordValidationBean.class.getName());

    /**
     * Create a new instance of {@code PasswordValidationBean}
     */
    public PasswordValidationBean() {
        super();
        logger.info("Create a new instance of " + getClass().getName());
    }

    /**
     * 
     * @param fc
     * @param uic
     * @param value
     */
    public void validate(FacesContext fc, UIComponent uic, Object value) {
        logger.info("validating password confirmation value");

        if (value != null) {
            confirmPassword = (String) value;
            if (confirmPassword.matches("\\s*")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password cannot be null nor empty!", "Password connot be empty!"));
            }
            // the password must have 7 characters at least
            if (confirmPassword.length() < 7) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password length must contain 7 characters at least", "Password must be 7 chars+ !"));
            }

            if (password == null) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Please fill the password field first", "Please fill the password field first"));
            }

            if (!password.equals(confirmPassword)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong input value",
                        "Password does not match!"));
            }
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The two values are identical and accepted!", "The two values are identical and accepted");
        fc.addMessage(uic.getClientId(fc), message);
        logger.info("validation of Password values passed with succes");
    }

    /**
     * 
     * @param fc
     * @param uic
     * @param o
     */
    public void validatePassword(FacesContext fc, UIComponent uic, Object o) {
        logger.info("validating password value");
        if (o != null) {
            password = (String) o;

            if (password.matches("\\s*")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password cannot be null nor empty!", "Password connot be empty!"));
            }
            // the password must have 7 characters at least
            if (password.length() < 7) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password length must contain 7 characters at least", "Password must be 7 chars+!"));
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Value accepted!", "The input value is accepted!");
        fc.addMessage(uic.getClientId(fc), message);
        logger.info("validation of Password values passed with succes");
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
