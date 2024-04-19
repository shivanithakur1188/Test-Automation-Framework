package com.taf.base;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import com.taf.utility.Library;

public class PreRequisites {

    public static void main(String[] args) {
        setupJunitAndCucumberPropertiesForParallelExecution();
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
}
