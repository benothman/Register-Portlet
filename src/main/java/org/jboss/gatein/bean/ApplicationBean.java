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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.model.SelectItem;
import org.jboss.gatein.util.ApplicationConfigLoader;
import org.jboss.gatein.xml.Properties;
import org.jboss.gatein.xml.Property;
import org.jboss.gatein.xml.UIComponents;

/**
 * {@code ApplicationBean}
 *
 * Created on Nov 15, 2010, 1:18:52 PM
 *
 * @author Nabil Benothman
 * @version 1.0
 */
public class ApplicationBean implements Serializable {

    private List<SelectItem> countries;
    private Map<String, Object> appData;
    private Properties props;
    private boolean loaded = false;
    private UIComponents uicomponents;
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String CANCEL = "cancel";
    public static final String RESET = "reset";
    public static final String VIEW = "view";
    private static final String PROPS_PATH = "/WEB-INF/profile-config.xml";
    private static final String UI_COMP_PATH = "/WEB-INF/ui-components-config.xml";
    public static final String[] COUNTRY_LIST = new String[]{
        "Abkhazia", "Afghanistan", "Akrotiri and Dhekelia", "≈land Islands",
        "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
        "Antigua and Barbuda", "Argentina ", "Armenia ", "Aruba", "Ascension Island",
        "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
        "Barbados", "Belarus", "Belgium", "Belize", "Benin ", "Bermuda", "Bhutan",
        "Bolivia", " Bosnia", "Botswana", "Brazil", "Brunei", " Bulgaria",
        "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
        "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
        "ChristmasIsland", "Cocos", "Colombia", "Comoros", "Congo", "Cook Islands",
        "Costa Rica", "CÙte d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech",
        "Denmark", "Djibouti", "Dominica", "Ecuador", "Egypt", "El Salvador",
        "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands",
        "Faroe Islands", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia",
        "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam",
        "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
        "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran",
        "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan",
        "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea", "Kosovo",
        "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
        "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia",
        "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
        "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico",
        "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat",
        "Morocco", "Mozambique", "Myanmar", "Nagorno-Karabakh", "Namibia", "Nauru",
        "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger",
        "Nigeria", "Niue", "Norfolk Island", "Norway", "Oman", "Pakistan", "Palau",
        "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
        "Pitcairn", "Poland", "Portugal", "Pridnestrovie", "Puerto Rico", "Qatar",
        "Romania", "Russia", "Rwanda", "Saint-BarthÈlemy", "Saint Helena",
        "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint-Pierre and	Miquelon",
        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "S„o TomÈ and PrÌncipe",
        "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
        "Singapore", "Slovakia", "Slovenia", "Solomon	Islands", "Somalia",
        "Somaliland", "South Africa", "South Ossetia", "Spain", "SriLanka", "Sudan",
        "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria",
        "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor", "Togo", "Tokelau",
        "Tonga", "Trinidad and Tobago", "Tristan da Cunha", "Tunisia", "Turkey",
        "Turkmenistan", "Turks and Caicos", "Tuvalu", "Uganda", "Ukraine",
        "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
        "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands",
        "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"
    };

    /**
     * Create a new instance of {@code ApplicationBean}
     */
    public ApplicationBean() {
        super();
    }

    /**
     * 
     */
    @PostConstruct
    public void init() {
        fillCountries();
    }

    //@PostConstruct
    public void initAppConfig() throws Exception {

        if (!this.isLoaded()) {
            this.appData = new ConcurrentHashMap<String, Object>();
            try {
                ApplicationConfigLoader appConfigLoader = ApplicationConfigLoader.getInstance();
                this.props = (Properties) appConfigLoader.xmlToObject(PROPS_PATH);
                // check if the props were loaded correctly
                for (Property prop : this.props.getProperties()) {
                    if (prop.getType().equals("java.util.Date")) {
                        this.appData.put(prop.getName(), new java.util.Date());
                    } else {
                        this.appData.put(prop.getName(), prop.getDisplayName().getText());
                    }
                }

                this.uicomponents = (UIComponents) appConfigLoader.xmlToObject(UI_COMP_PATH);
                // check if the uicomponents were loaded correctly
                this.loaded = true;
            } catch (Exception exp) {
                System.err.println(exp.getMessage());
            }
        }
        System.out.println("init application config finish");
    }

    @PreDestroy
    public void destroy() {
        this.loaded = false;
        this.appData = null;
        this.props = null;
        this.uicomponents = null;
        this.countries = null;
    }

    /**
     * Retrieve the list of all available countries and fill the list of select
     * items
     */
    private void fillCountries() {
        if (this.countries == null) {
            this.countries = new ArrayList<SelectItem>();
            Locale[] locales = Locale.getAvailableLocales();
            String country = null;

            // need to sort the list to get a sorted list of countries
            SortedSet<String> sortedSet = new TreeSet<String>();

            for (Locale locale : locales) {
                country = locale.getDisplayCountry();
                sortedSet.add(country);
            }

            // add the list of available coutries to the list of select items
            for (String str : sortedSet) {
                this.countries.add(new SelectItem(str, str));
            }
        }
    }

    /**
     * @return the counties
     */
    public List<SelectItem> getCountries() {
        return countries;
    }

    /**
     * @param countries the counties to set
     */
    public void setCountries(List<SelectItem> countries) {
        this.countries = countries;
    }

    /**
     * @return the loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * @return the props
     */
    public Properties getProps() {
        return props;
    }

    /**
     * @return the UI components
     */
    public UIComponents getUicomponents() {
        return uicomponents;
    }

    /**
     * @return the appData
     */
    public Map<String, Object> getAppData() {
        return appData;
    }
}
