package org.mvander3.util.spring;

import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringMain {
    
    private static Logger log = Logger.getLogger(SpringMain.class);
    
    private static final String CONFIG_FILES_PROPERTY_NAME = "config.files";
    private static final String CONFIG_FILES_SEPARATOR = ",";

    public static void main(String[] args) {
        String configFileLocationsProperty = System.getProperty(CONFIG_FILES_PROPERTY_NAME);
        String[] configFileLocations = configFileLocationsProperty.split(CONFIG_FILES_SEPARATOR);
        log.info("Starting application using files: " + configFileLocations);
        new FileSystemXmlApplicationContext(configFileLocations);
        log.info("Application started");
    }

}
