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

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import nl.captcha.Captcha;
import org.jboss.gatein.bean.MediaBean;

/**
 * {@code CaptchaValidatorBean}
 *
 * Created on Nov 18, 2010, 9:15:51 AM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class CaptchaValidatorBean implements Validator {

    private MediaBean mediaBean;

    /**
     * Create a new instance of {@code CaptchaValidatorBean}
     */
    public CaptchaValidatorBean() {
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
            //retrieve the resoure bundle of the application
            ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(fc, "msg");
            String validationMessage = null;
            if (value.matches("\\s*")) {
                validationMessage = resourceBundle.getString(UIInput.REQUIRED_MESSAGE_ID);
                if (validationMessage == null) {
                    validationMessage = "Value is required!";
                }

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }

            Captcha captcha = this.mediaBean.getCaptcha();

            if (!captcha.isCorrect(value)) {
                this.mediaBean.initCaptcha();
                validationMessage = resourceBundle.getString("gatein.captcha.answer.error");
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validationMessage, validationMessage));
            }
            
            validationMessage = resourceBundle.getString("gatein.captcha.answer.correct");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, validationMessage, validationMessage);
            fc.addMessage(uic.getClientId(fc), message);
        }
    }

    /**
     * @return the mediaBean
     */
    public MediaBean getMediaBean() {
        return mediaBean;
    }

    /**
     * @param mediaBean the mediaBean to set
     */
    public void setMediaBean(MediaBean mediaBean) {
        this.mediaBean = mediaBean;
    }
}
