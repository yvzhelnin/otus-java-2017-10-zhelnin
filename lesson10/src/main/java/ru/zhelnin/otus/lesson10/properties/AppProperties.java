package ru.zhelnin.otus.lesson10.properties;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("Couldn't read jdbc.properties");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
