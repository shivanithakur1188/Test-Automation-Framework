package com.taf.base;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taf.api.endpoints.APIEndPoints;
import com.taf.api.utilities.Constants;
import com.taf.api.utilities.JsonPath;
import com.taf.api.utilities.PropertyHolder;
import com.taf.api.utilities.Utility;
import com.taf.utility.Library;

public class Base {
    public static final Logger LOGGER = LoggerFactory.getLogger(Base.class);
    public static Properties envProperties = new Properties();


    static {
        init();
        readPropertyFiles();
        readEnvPropertyFile();
    }

    /**
     * Initialize config.property available under resource folder
     */
    public static void init() {
        try {
            Utility.putVariablesInMap(APIEndPoints.class);
            Utility.putVariablesInMap(JsonPath.class);
            Utility.putVariablesInMap(Constants.class);
        } catch (Exception e) {
            Assert.assertTrue("Error while reading properties file: " + e.getMessage(), false);
        }
    }

    public static void readPropertyFiles() {
        try {
            Properties commonProps = new Properties();
            commonProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config/properties/common.properties"));

            Properties apiProps = new Properties();
            commonProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config/properties/api/api.properties"));

            Properties uiProps = new Properties();
            commonProps.load(new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config/properties/ui/ui.properties"));

            Library.loadPropertiesInPropertyHolder(commonProps);
            Library.loadPropertiesInPropertyHolder(apiProps);
            Library.loadPropertiesInPropertyHolder(uiProps);
        } catch (IOException e) {
            Assert.assertTrue("Error on reading config file" + e.getMessage(), false);
        }
    }

    public static void readEnvPropertyFile() {
        try {
            String environment =
                Library.getEnvProperty("environment") == null ? "qa" : Library.getEnvProperty("environment");
            FileInputStream stream = new FileInputStream(System.getProperty("user.dir")
                + "/src/test/resources/config/environments/" + environment + ".properties");
            envProperties.load(stream);
            Library.loadPropertiesInPropertyHolder(envProperties);
        } catch (IOException e) {
            Assert.assertTrue("Error on reading environment config file" + e.getMessage(), false);
        }
    }

}
