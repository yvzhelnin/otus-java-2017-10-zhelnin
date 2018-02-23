package ru.zhelnin.otus.lesson15.app;

import ru.zhelnin.otus.lesson15.cache.model.CacheData;

public interface CacheService {

    void init();

    CacheData getCacheData();
}
