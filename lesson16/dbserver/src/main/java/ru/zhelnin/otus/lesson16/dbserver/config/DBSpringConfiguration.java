package ru.zhelnin.otus.lesson16.dbserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.zhelnin.otus.lesson16.dbserver.cache.ZCache;
import ru.zhelnin.otus.lesson16.dbserver.cache.ZCacheImpl;
import ru.zhelnin.otus.lesson16.dbserver.generator.DataGenerator;
import ru.zhelnin.otus.lesson16.dbserver.model.UserData;
import ru.zhelnin.otus.lesson16.dbserver.service.UserDataService;
import ru.zhelnin.otus.lesson16.dbserver.service.impl.UserDataServiceImpl;

@Configuration
public class DBSpringConfiguration {

    @Bean
    public ZCache<Long, UserData> zCache() {
        return new ZCacheImpl<>(5, 10);
    }

    @Bean
    public UserDataService userDataService() {
        return new UserDataServiceImpl(zCache());
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean(initMethod = "init")
    public DataGenerator dataGenerator() {
        return new DataGenerator(userDataService(), threadPoolTaskExecutor());
    }
/*
    @Bean(initMethod = "init")
    public DBMessageWorker dbMessageWorker() throws IOException {
        return new DBMessageWorker(new Socket(BaseConstants.SOCKET_HOST, BaseConstants.SOCKET_PORT));
    }

    @Bean(initMethod = "start")
    public DBSocketClient socketClient() throws IOException {
        return new DBSocketClient(dbMessageWorker());
    }*/
}
