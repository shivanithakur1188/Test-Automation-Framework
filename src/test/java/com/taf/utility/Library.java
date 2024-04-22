package com.taf.utility;

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

}
