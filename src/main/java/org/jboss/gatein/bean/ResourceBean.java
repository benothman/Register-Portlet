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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

/**
 * {@code ResourceBean}
 *
 * Created on Nov 25, 2010, 5:32:01 PM
 *
 * @author nabilbenothman
 * @version 1.0
 */
public class ResourceBean implements Map<String, String> {

    /**
     * Create a new instance of {@code ResourceBean}
     */
    public ResourceBean() {
        super();
    }

    public void clear() {
    }

    public boolean containsKey(Object key) {
        return true;
    }

    public boolean containsValue(Object value) {
        return true;
    }

    public Set<java.util.Map.Entry<String, String>> entrySet() {
        return Collections.emptySet();
    }

    /*
     * (non-Javadoc)
     * @see java.util.Map.get(java.lang.Object)
     */
    public String get(Object key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String url = null;
        if (null == key) {
            url = null;
        } else if (null != facesContext) {
            url = facesContext.getApplication().getViewHandler().getResourceURL(facesContext, key.toString());
            url = facesContext.getExternalContext().encodeResourceURL(url);
        } else {
            url = key.toString();
        }
        return url;
    }

    public boolean isEmpty() {
        return false;
    }

    public Set<String> keySet() {
        return Collections.emptySet();
    }

    public String put(String key, String value) {
        return null;
    }

    public void putAll(Map<? extends String, ? extends String> t) {
    }

    public String remove(Object key) {
        return null;
    }

    public int size() {
        return 0;
    }

    public Collection<String> values() {
        return Collections.emptySet();
    }
}
