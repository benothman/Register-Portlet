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
package org.jboss.gatein.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.faces.model.SelectItem;

/**
 * {@code ApplicationBean}
 *
 * Created on Nov 15, 2010, 1:18:52 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class ApplicationBean {

    private List<SelectItem> counties;
    public static String[] CountryList = new String[]{"Abkhazia", "Afghanistan", "Akrotiri and Dhekelia",
        "≈land Islands", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
        "Antigua and Barbuda", "Argentina ", "Armenia ", "Aruba", "Ascension Island",
        "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
        "Belarus", "Belgium", "Belize", "Benin ", "Bermuda", "Bhutan", "Bolivia", " Bosnia", "Botswana", "Brazil",
        "Brunei", " Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
        "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "ChristmasIsland",
        "Cocos", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "CÙte d'Ivoire",
        "Croatia", "Cuba", "Cyprus", "Czech", "Denmark", "Djibouti", "Dominica", "Ecuador", "Egypt",
        "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands",
        "Faroe Islands", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
        "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea",
        "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
        "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey",
        "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
        "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao",
        "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
        "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia",
        "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Nagorno-Karabakh", "Namibia", "Nauru",
        "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
        "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru",
        "Philippines", "Pitcairn", "Poland", "Portugal", "Pridnestrovie", "Puerto Rico", "Qatar",
        "Romania", "Russia", "Rwanda", "Saint-BarthÈlemy", "Saint Helena", "Saint Kitts and Nevis",
        "Saint Lucia", "Saint Martin", "Saint-Pierre and	Miquelon",
        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "S„o TomÈ and PrÌncipe",
        "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia",
        "Slovenia", "Solomon	Islands", "Somalia", "Somaliland", "South Africa", "South Ossetia",
        "Spain", "SriLanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden",
        "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor",
        "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tristan da Cunha",
        "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos", "Tuvalu", "Uganda",
        "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
        "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
        "Virgin Islands", "Wallis and Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};

    /**
     * Create a new instance of {@code ApplicationBean}
     */
    public ApplicationBean() {
        this.counties = new ArrayList<SelectItem>();
        fillCountries();
    }

    /**
     * Retrieve the list of all available countries and fill the list of select
     * items
     */
    private void fillCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        String country = null;
        List<String> tmp = new ArrayList<String>();

        for (Locale locale : locales) {
            country = locale.getDisplayCountry();
            if (!country.equals("") && !tmp.contains(country)) {
                tmp.add(country);
            }
        }

        // need to sort the list to get a sorted list of countries
        Collections.sort(tmp);
        // add the list of available coutries to the list of select items
        for (String str : tmp) {
            this.counties.add(new SelectItem(str, str));
        }
    }

    /**
     * @return the counties
     */
    public List<SelectItem> getCounties() {
        return counties;
    }

    /**
     * @param counties the counties to set
     */
    public void setCounties(List<SelectItem> counties) {
        this.counties = counties;
    }
}
