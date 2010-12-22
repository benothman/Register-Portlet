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
package org.jboss.gatein.bean;

import java.awt.Color;
import java.io.Serializable;

/**
 * {@code MediaData}
 *
 * Created on Dec 1, 2010, 3:18:02 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class MediaData implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer Width = 200;
    Integer Height = 50;
    Color Background = new Color(0, 0, 0);
    Color DrawColor = new Color(255, 255, 255);

    /**
     * Create a new instance of {@code MediaData}
     */
    public MediaData() {
        super();
    }

    public Color getBackground() {
        return Background;
    }

    public void setBackground(Color background) {
        Background = background;
    }

    public Color getDrawColor() {
        return DrawColor;
    }

    public void setDrawColor(Color drawColor) {
        DrawColor = drawColor;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public Integer getWidth() {
        return Width;
    }

    public void setWidth(Integer width) {
        Width = width;
    }
}
