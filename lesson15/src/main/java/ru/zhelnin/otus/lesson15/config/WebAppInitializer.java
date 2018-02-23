package ru.zhelnin.otus.lesson15.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements WebApplicationInitializer {

    private static AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        context.register(SpringConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(context));
    }

    public static AnnotationConfigWebApplicationContext getContext() {
        return context;
    }
}
