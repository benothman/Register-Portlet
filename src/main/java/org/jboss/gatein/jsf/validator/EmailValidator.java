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
import java.util.regex.Matcher;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * {@code EmailValidator}
 * Checks that a given string is a well-formed email address.
 * <p>
 * The specification of a valid email can be found in
 * <a href="http://www.faqs.org/rfcs/rfc2822.html">RFC 2822</a>
 * and one can come up with a regular expression matching <a href="http://www.ex-parrot.com/~pdw/Mail-RFC822-Address.html">
 * all valid email addresses</a> as per specification. However, as this
 * <a href="http://www.regular-expressions.info/email.html">article</a> discusses it is not necessarily practical to
 * implement a 100% compliant email validator. This implementation is a trade-off trying to match most email while ignoring
 * for example emails with double quotes or comments.
 * </p>
 * <p> This implementation is inspired from the Hibernate {@code EmailValidator} implementation</p>
 *
 *
 * Created on Nov 20, 2010, 11:14:09 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class EmailValidator implements Validator {

    private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
    private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
    private static final java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "^" + ATOM + "+(\\." + ATOM + "+)*@"
            + DOMAIN
            + "|"
            + IP_DOMAIN
            + ")$",
            java.util.regex.Pattern.CASE_INSENSITIVE);

    /**
     * Create a new instance of {@code EmailValidator}
     */
    public EmailValidator() {
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

            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String validationMessage = null;

            if (value.trim().matches("\\s*")) {
                validationMessage = resourceBundle.getString(UIInput.REQUIRED_MESSAGE_ID);
                if (validationMessage == null) {
                    validationMessage = "Value is required!";
                }

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            if (!isValid(value)) {
                validationMessage = resourceBundle.getString("gatein.email.format.invalid");
                if (validationMessage == null) {
                    validationMessage = "Invalid email format!";
                }

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }
            validationMessage = resourceBundle.getString("gatein.email.format.valid");
            if (validationMessage == null) {
                validationMessage = "E-mail address well formed!";
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }

    /**
     * 
     * @param value
     * @return
     */
    public boolean isValid(String value) {
        if (value == null || value.length() == 0) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
