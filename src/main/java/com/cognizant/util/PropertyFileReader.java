package com.cognizant.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private Properties properties;

    public PropertyFileReader(String filePath) {
        properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read property file: " + filePath, exception);
        }
    }

    public String getValue(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Property key not found: " + key);
        }

        return value;
    }

    public String getValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
