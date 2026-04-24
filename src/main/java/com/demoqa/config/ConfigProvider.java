package com.demoqa.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ConfigProvider.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("File config.properties not found in folder resources!");
            }
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error reading configuration file: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}