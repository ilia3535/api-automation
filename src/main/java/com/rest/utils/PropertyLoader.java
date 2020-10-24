package com.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertyLoader {
    private static Properties properties = loadProperties();

    public static String getUri() {
        return properties.getProperty("uri");
    }

    public static Integer getPort() {
        return Integer.valueOf(properties.getProperty("port"));
    }

    public static void main(String[] args) {
        String value = PropertyLoader.getUri();
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();

        String environment = System.getProperty("env", "qa");
        try (InputStream input = new FileInputStream(String.format("application-%s.properties", environment))) {
            prop.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return prop;
    }
}
