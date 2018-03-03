package ru.zhelnin.otus.lesson16.dbserver.cache.service;

import ru.zhelnin.otus.lesson16.core.model.CacheData;
import ru.zhelnin.otus.lesson16.dbserver.app.CacheService;
import ru.zhelnin.otus.lesson16.dbserver.cache.ZCache;

public class CacheServiceImpl implements CacheService {

    private final ZCache cache;

    public CacheServiceImpl(ZCache cache) {
        this.cache = cache;
    }

    @Override
    public CacheData getCacheData() {
        return new CacheData(cache.getMaxSize(), cache.getCurrentSize(), cache.getHitsCounter(), cache.getMissesCounter());
    }
}
