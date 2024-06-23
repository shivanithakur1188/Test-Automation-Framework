package com.taf.base;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.taf.utility.Library;

public class PreRequisites {

    public static void main(String[] args) {
        setupJunitAndCucumberPropertiesForParallelExecution();
        setupSerenityProperties();
    }

    private static void setupJunitAndCucumberPropertiesForParallelExecution() {
        try {
            Properties junitProperties = new Properties();
            Properties cucumberProperties = new Properties();

            // to create junit-platform.properties, if already exists, load it.
            try {
                junitProperties.load(new FileReader(System.getProperty("user.dir")
                    + "/src/test/resources/junit-platform.properties"));
            } catch (IOException e) {
                junitProperties.put("cucumber.execution.parallel.enabled", "true");
                junitProperties.put("cucumber.plugin",
                    "io.cucumber.core.plugin.SerenityReporter,pretty,timeline:target/test-results/timeline");
            }

            if (Library.getEnvProperty("parallelCount") != null) {
                junitProperties.put("cucumber.execution.parallel.config.fixed.parallelism",
                    Library.getEnvProperty("parallelCount"));
                junitProperties.put("cucumber.execution.parallel.config.fixed.max-pool-size",
                    Library.getEnvProperty("parallelCount"));
            } else {
                junitProperties.put("cucumber.execution.parallel.config.fixed.parallelism", "1");
                junitProperties.put("cucumber.execution.parallel.config.fixed.max-pool-size", "1");
            }
            if (Library.getEnvProperty("parallelType") != null) {
                if (Library.getEnvProperty("parallelType").equalsIgnoreCase("scenario")) {
                    junitProperties.put("cucumber.execution.parallel.config.strategy", "fixed");
                } else {
                    junitProperties.put("cucumber.execution.execution-mode.feature", "same_thread");
                    junitProperties.put("cucumber.execution.parallel.config.strategy", "fixed");
                }
            } else {
                junitProperties.put("cucumber.execution.execution-mode.feature", "same_thread");
                junitProperties.put("cucumber.execution.parallel.config.strategy", "fixed");
            }

            junitProperties.store(
                new FileWriter(System.getProperty("user.dir") + "/src/test/resources/junit-platform.properties"),
                "junit-platform property file updated " + "\n\n");
            Base.LOGGER.info("junit-platform.properties file loaded successfully.");

            // to create cucumber.properties, if already exists, load it.
            try {
                cucumberProperties.load(new FileReader(System.getProperty("user.dir")
                    + "/src/test/resources/cucumber.properties"));
            } catch (IOException e) {
                cucumberProperties.put("cucumber.snippet-type", "camelcase");
                cucumberProperties.put("cucumber.execution.order", "random");
                cucumberProperties.put("cucumber.plugin",
                    "pretty,timeline:target/test-results/timeline,json:target/cucumber.json");
            }

            cucumberProperties.store(
                new FileWriter(System.getProperty("user.dir") + "/src/test/resources/cucumber.properties"),
                "cucumber property file updated " + "\n\n");
            Base.LOGGER.info("cucumber.properties file loaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setupSerenityProperties() {
        try {
            //load common property to get local property value
            Properties serenityProperties = new Properties();
            serenityProperties.load(
                new FileReader(System.getProperty("user.dir") + "/src/test/resources/serenityCommon.properties"));


            // setting environment properties
            serenityProperties.put("environment", Library.getEnvProperty("environment") == null ? "qa"
                : Library.getEnvProperty("environment"));

            // setting driver properties
            serenityProperties.put("webdriver.driver", Library.getEnvProperty("driver") == null ? "chrome"
                : Library.getEnvProperty("driver"));

            // setting report properties
            serenityProperties.put("report.customfields.environment", System.getenv("environment") == null ? "QA"
                : System.getenv("environment").toUpperCase());
            serenityProperties.put("report.customfields.tags", System.getenv("tags") == null ? "@all"
                : System.getenv("tags"));
            serenityProperties.put("report.customfields.browser",
                System.getenv("browser") == null ? System.getProperty("browser") == null ? "CHROME"
                    : System.getProperty("browser").toUpperCase()
                    : System.getenv("browser").toUpperCase()
            );
            serenityProperties.put("serenity.report.url", System.getProperty("user.dir") + "/target/site/serenity");

            // setting headless based on variables from cmd/jenkins
            if (Library.getEnvProperty("headless") != null) {
                if (Library.getEnvProperty("headless").equals("true")) {
                    serenityProperties.put(
                        serenityProperties.get("webdriver.driver").toString().toLowerCase() + ".switches",
                        "--headless=new;" + serenityProperties.get(
                            serenityProperties.get("webdriver.driver").toString().toLowerCase() + ".switches"));
                }
            }

            serenityProperties.store(new FileWriter(System.getProperty("user.dir") + "/serenity.properties"),
                "Property File created from: \n/src/test/resources/serenityCommon.properties\n"
                    + "\n\n");
            Base.LOGGER.info("serenity.properties file loaded successfully.");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
