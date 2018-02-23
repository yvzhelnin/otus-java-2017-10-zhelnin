package ru.zhelnin.otus.lesson15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.zhelnin.otus.lesson15.app.CacheService;
import ru.zhelnin.otus.lesson15.app.MessageSystemContext;
import ru.zhelnin.otus.lesson15.cache.ZCache;
import ru.zhelnin.otus.lesson15.cache.ZCacheImpl;
import ru.zhelnin.otus.lesson15.cache.service.CacheServiceImpl;
import ru.zhelnin.otus.lesson15.generator.DataGenerator;
import ru.zhelnin.otus.lesson15.message.MessageSystem;
import ru.zhelnin.otus.lesson15.model.UserData;
import ru.zhelnin.otus.lesson15.service.UserDataService;
import ru.zhelnin.otus.lesson15.service.impl.UserDataServiceImpl;

@Configuration
public class SpringConfiguration {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);

        return executor;
    }

    @Bean
    public ZCache<Long, UserData> zCache() {
        return new ZCacheImpl<>(5, 10);
    }

    @Bean
    public UserDataService userDataService() {
        return new UserDataServiceImpl(zCache());
    }

    @Bean(initMethod = "init")
    public DataGenerator dataGenerator() {
        return new DataGenerator(userDataService(), threadPoolTaskExecutor());
    }

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystem(threadPoolTaskExecutor());
    }

    @Bean
    public MessageSystemContext messageSystemContext() {
        return new MessageSystemContext(messageSystem());
    }

    @Bean(initMethod = "init")
    public CacheService cacheService() {
        return new CacheServiceImpl(zCache(), messageSystemContext());
    }
}
