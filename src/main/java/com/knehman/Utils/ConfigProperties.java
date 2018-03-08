package com.knehman.Utils;

import java.util.HashMap;
import java.util.Map;

public class ConfigProperties {

    private Map<String, Object> properties;

    ConfigProperties() {
        this.properties = new HashMap<String, Object>();
    }

    public void put(String key, Object o) {
        this.properties.put(key, o);
    }

    public String getString(String key) {
        return this.properties.get(key).toString();
    }

    public int getInt(String key) {
        return (int) this.properties.get(key);
    }

    public double getDouble(String key) {
        return (double) this.properties.get(key);
    }

    public void debugPrint() {
        System.out.println("Config properties map:");
        for (String key : this.properties.keySet()) {
            System.out.println("\"" + key + "\": \"" + this.properties.get(key) + "\"");
        }
    }
}
