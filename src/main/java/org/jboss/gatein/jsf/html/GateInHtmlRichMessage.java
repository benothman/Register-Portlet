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
import javax.faces.context.FacesContext;
import org.richfaces.component.html.HtmlRichMessage;

/**
 * {@code GateInHtmlRichMessage}
 *
 * Created on Dec 8, 2010, 3:21:59 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class GateInHtmlRichMessage extends HtmlRichMessage {

    private boolean alreadyRendered = false;

    /**
     * Create a new instance of {@code GateInHtmlRichMessage}
     */
    public GateInHtmlRichMessage() {
        super();
    }

    @Override
    public void encodeAll(FacesContext fc) throws IOException {
        if (!isAlreadyRendered()) {
            super.encodeAll(fc);
            this.setAlreadyRendered(true);
        }
    }

    @Override
    public void encodeBegin(FacesContext fc) throws IOException {
        if (!isAlreadyRendered()) {
            super.encodeBegin(fc);
        }
    }

    @Override
    public void encodeEnd(FacesContext fc) throws IOException {
        if (!isAlreadyRendered()) {
            super.encodeEnd(fc);
            this.setAlreadyRendered(true);
        }
    }

    /**
     * Getter for alreadyRendered
     *
     * @return the alreadyRendered boolean value
     */
    public boolean isAlreadyRendered() {
        return alreadyRendered;
    }

    /**
     * Setter for alreadyRendered
     * 
     * @param alreadyRendered the alreadyRendered to set
     */
    public void setAlreadyRendered(boolean alreadyRendered) {
        this.alreadyRendered = alreadyRendered;
    }
}
