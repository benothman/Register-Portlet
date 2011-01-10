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

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.jboss.gatein.jsf.converter.GateInInputTextConverter;

/**
 * {@code GateInHtmlInputText}
 *
 * Created on Nov 15, 2010, 10:35:50 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlInputText extends HtmlInputText implements Serializable {

    public static final String COMPONENT_TYPE = "org.jboss.gatein.jsf.html.GateInHtmlInputText";

    /**
     * Create a new instance of {@code GateInHtmlInputText}
     */
    public GateInHtmlInputText() {
        super();
        this.setConverter(new GateInInputTextConverter());
    }

    @Override
    public void validate(FacesContext fc) {
        Object submittedValue = getSubmittedValue();
        if (submittedValue == null) { // the value was not submitted at all
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
                this.setValue(this.getLabel());
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

    /*
    @Override
    public Object getSubmittedValue() {
        String value = (String) super.getSubmittedValue();
        String label = getLabel();
        return (value != null && value.trim().equalsIgnoreCase(label)) ? "" : value;
    }
    */

    @Override
    public String getTitle() {
        String title = super.getTitle();
        return (title == null || title.trim().length() == 0) ? this.getLabel() : title;
    }

    @Override
    public Object getValue() {
        Object val = super.getValue();
        String stringVal = (String) val;

        return (val == null || stringVal.trim().length() == 0) ? this.getLabel() : val;
    }
}
