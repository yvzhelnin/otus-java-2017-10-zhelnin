package ru.zhelnin.otus.lesson16.dbserver.properties;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DBProperties {

    private static final Logger logger = Logger.getLogger(DBProperties.class.getName());

    private static final String PROPERTIES_FILE_NAME = "hibernate.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.severe("Couldn't read " + PROPERTIES_FILE_NAME);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
