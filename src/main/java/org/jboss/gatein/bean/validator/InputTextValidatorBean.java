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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.jboss.gatein.jsf.validator.GateInInputTextValidator;

/**
 * {@code InputTextValidatorBean}
 *
 * Created on Dec 6, 2010, 1:13:59 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class InputTextValidatorBean extends GateInInputTextValidator {

    /**
     * Create a new instance of {@code InputTextValidatorBean}
     */
    public InputTextValidatorBean() {
        super();
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        try {
            super.validate(fc, uic, o);
        } catch (ValidatorException vexp) {
            throw vexp;
        }
    }
}
