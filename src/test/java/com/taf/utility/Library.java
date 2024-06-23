package com.taf.utility;

import java.util.Properties;
import java.util.Set;

import com.taf.api.utilities.PropertyHolder;

public class Library {
    
    public static String getEnvProperty(String propertyName) {
        if (System.getenv().containsKey(propertyName)) {
            return System.getenv(propertyName);
        } else if (System.getProperties().containsKey(propertyName)) {
            return System.getProperty(propertyName);
        } else {
            return null;
        }
    }

    public static void loadPropertiesInPropertyHolder(Properties properties){
        if (!properties.isEmpty()) {
            Set<Object> keys = properties.keySet();
            for (Object object : keys) {
                PropertyHolder.setProperty(object.toString(), properties.getProperty(object.toString()));
            }
        }
    }

}
