package ru.zhelnin.otus.lesson12.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.zhelnin.otus.lesson12.cache.ZCache;
import ru.zhelnin.otus.lesson12.cache.ZCacheImpl;
import ru.zhelnin.otus.lesson12.generator.DataGenerator;
import ru.zhelnin.otus.lesson12.model.UserData;
import ru.zhelnin.otus.lesson12.service.UserDataService;
import ru.zhelnin.otus.lesson12.service.impl.UserDataServiceImpl;
import ru.zhelnin.otus.lesson12.servlet.CacheServlet;
import ru.zhelnin.otus.lesson12.servlet.LoginServlet;
import util.Constants;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(Constants.PAGES_PATH);
        ZCache<Long, UserData> users = new ZCacheImpl<>(2, 10);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet(Constants.USER_NAME, Constants.PASSWORD)), "/login");
        context.addServlet(new ServletHolder(new CacheServlet(users)), "/cache/getData");

        Server server = new Server(Constants.PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        startDataGeneration(users);
        server.join();
    }

    private static void startDataGeneration(ZCache<Long, UserData> cache) throws SQLException, IOException {
        try (UserDataService service = new UserDataServiceImpl(cache)) {
            DataGenerator.execute(service);
        }
    }
}
