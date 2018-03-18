package ru.zhelnin.otus.lesson16.dbserver.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.zhelnin.otus.lesson16.dbserver.config.DBServerApplicationContextInitializer;
import ru.zhelnin.otus.lesson16.dbserver.config.DBSpringConfiguration;

public class DBServerApplication {

    public static void main(String[] args) {
        DBServerApplicationContextInitializer initializer = new DBServerApplicationContextInitializer();
        initializer.initialize(new AnnotationConfigApplicationContext(DBSpringConfiguration.class));
    }
}
