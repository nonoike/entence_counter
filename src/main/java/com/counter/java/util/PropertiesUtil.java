package com.counter.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesUtil {
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    public static String getValue(String key) {
        try (InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            Properties properties = new Properties();
            properties.load(br);

            return properties.getProperty(key, "");
        } catch (IOException e) {
            // TODO: consider the exception
            throw new RuntimeException(e);
        }
    }
}
