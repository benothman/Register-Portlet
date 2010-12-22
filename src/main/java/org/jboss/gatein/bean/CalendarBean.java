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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * {@code CalendarBean}
 * Utility class for calendar
 * 
 * Created on Nov 8, 2010, 2:14:35 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class CalendarBean implements Serializable {

    private boolean popup = true;
    private boolean showApply = false;
    private String pattern;
    private Date selectedDate;
    private Locale locale;
    private Calendar cal;

    /**
     * Creates a new instance of {@code CalendarBean}
     */
    public CalendarBean() {
        super();
    }

    @PostConstruct
    public void init() {
        this.popup = true;
        cal = Calendar.getInstance();
        this.selectedDate = cal.getTime();
        ExternalContext exCtx = FacesContext.getCurrentInstance().getExternalContext();
        this.locale = exCtx.getRequestLocale();
        this.pattern = ((SimpleDateFormat) DateFormat.getDateInstance(SimpleDateFormat.MEDIUM, this.locale)).toPattern();
    }

    /**
     *
     * @param event
     */
    public void filter(ValueChangeEvent event) {
        try {
            cal.setTime(selectedDate);
            cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) - 1);
            cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) + 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        } catch (Exception exp) {
            System.err.println(exp.getMessage());
        }
    }

    /**
     * 
     * @param event
     */
    public void selectLocale(ValueChangeEvent event) {

        String str = (String) event.getNewValue();

        if (str.equals("en/US")) {
            this.locale = Locale.US;
        } else if (str.equals("fr/FR")) {
            this.locale = Locale.FRANCE;
        } else if (str.equals("de/DE")) {
            this.locale = Locale.GERMANY;
        }
    }

    /**
     * @return the popup
     */
    public boolean isPopup() {
        return popup;
    }

    /**
     * @param popup the popup to set
     */
    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    /**
     * @return the showApply
     */
    public boolean isShowApply() {
        return showApply;
    }

    /**
     * @param showApply the showApply to set
     */
    public void setShowApply(boolean showApply) {
        this.showApply = showApply;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the selectedDate
     */
    public Date getSelectedDate() {
        return selectedDate;
    }

    /**
     * @param selectedDate the selectedDate to set
     */
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * @return the local
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param local the local to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
