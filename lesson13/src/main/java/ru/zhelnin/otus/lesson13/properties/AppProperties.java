package ru.zhelnin.otus.lesson13.properties;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final String PROPERTIES_FILE_NAME = "hibernate.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("Couldn't read " + PROPERTIES_FILE_NAME);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
