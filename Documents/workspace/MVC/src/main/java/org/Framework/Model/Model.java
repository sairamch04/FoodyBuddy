package org.Framework.Model;

import java.util.HashMap;
import java.util.Map;
/**
 * Contains the Map which will be added as parameter to request
 */
public class Model {
    Map<String, Object> map;

    public Model() {
        map = new HashMap<String,Object>();
    }

    public Map<String, Object> getMap() { return map; }

    void addAttribute(Object value) {
        Class<?> ty = value.getClass();
        String name = ty.getName();
        addAttribute(name, value);
    }

    void addAttribute(String name, Object value) {
        map.put(name, value);
    }
    
    Object getAttribute(String name) {
        return map.get(name);
    }
}