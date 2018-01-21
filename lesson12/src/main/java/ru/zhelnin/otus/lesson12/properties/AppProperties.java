package ru.zhelnin.otus.lesson12.properties;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("Couldn't read hibernate.properties");
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
