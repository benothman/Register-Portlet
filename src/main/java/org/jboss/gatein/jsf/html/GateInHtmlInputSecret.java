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
package org.jboss.gatein.jsf.html;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;

/**
 * {@code GateInHtmlInputSecret}
 *
 * Created on Dec 17, 2010, 3:59:30 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputSecret extends HtmlInputSecret implements Serializable {

    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInHtmlInputSecret";

    /**
     * Create a new instance of {@code GateInHtmlInputSecret}
     */
    public GateInHtmlInputSecret() {
        super();
        this.setValue(this.getLabel());
    }

    @Override
    public void validate(FacesContext fc) {
        Object submittedValue = getSubmittedValue();
        if (submittedValue == null) {
            return;
        }
        ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
        String validationMessage = null;


        String newValue = (String) submittedValue;
        if (this.isRequired() && newValue.matches("\\s*")) {
            String requiredMessageStr = getRequiredMessage();
            FacesMessage message;
            if (null != requiredMessageStr) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        requiredMessageStr,
                        requiredMessageStr);
            } else {
                validationMessage = resourceBundle.getString(UIInput.REQUIRED_MESSAGE_ID);
                if (validationMessage == null) {
                    validationMessage = "Value is required!";
                }

                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage);
            }

            this.setStyle("border:solid 2px #FF0000;");
            fc.addMessage(getClientId(fc), message);
            setValid(false);
            return;
        }

        super.validate(fc);
        this.setStyle(this.isValid() ? "" : "border:solid 2px #FF0000;");
    }

    @Override
    public Object getSubmittedValue() {
        String value = (String) super.getSubmittedValue();
        String label = getLabel();
        if (value != null && value.trim().equalsIgnoreCase(label)) {
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
