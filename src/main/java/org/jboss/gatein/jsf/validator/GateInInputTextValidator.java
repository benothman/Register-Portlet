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
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;

/**
 * {@code GateInInputTextValidator}
 *
 * Created on Nov 15, 2010, 11:58:02 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInInputTextValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(GateInInputTextValidator.class.getName());

    /**
     * Create a new instance of {@code GateInInputTextValidator}
     */
    public GateInInputTextValidator() {
        super();
        logger.info("create new instance of " + getClass().getName());
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator.validate(javax.faces.context.FacesContext,
     *   javax.faces.component.UIComponent, java.lang.Object)
     */
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        logger.info("validating a new HtmlInputText value");
        if (o != null) {
            if (!(o instanceof String)) {
                logger.error("Illegal argument exception : " + o);
                throw new IllegalArgumentException("The value must be a String");
            }
            // remove spaces at the begining and the end of the string value
            String value = ((String) o).trim();
            HtmlInputText inputText = (HtmlInputText) uic;

            if (value.matches("\\s*")) {
                logger.error("value is required");
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is required!",
                        "The input value connot be empty"));
            }

            String label = inputText.getLabel();
            if (value.equalsIgnoreCase(label)) {
                logger.error("Invalid input value");
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "The introduced value is not valid!",
                        "The value " + value + " is not accepted!"));
            }
            // if validation passes with success, hide bubble info
            //inputText.setStyle("background-color: #FFFFFF;");
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "The introduced value is accpeted", "The introduced value is accpeted");
        fc.addMessage(uic.getClientId(fc), message);
        logger.info("validation of HtmlInputText value passed with succes");
    }
}
