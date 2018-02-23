package ru.zhelnin.otus.lesson15.cache.service;

import ru.zhelnin.otus.lesson15.app.CacheService;
import ru.zhelnin.otus.lesson15.app.MessageSystemContext;
import ru.zhelnin.otus.lesson15.cache.ZCache;
import ru.zhelnin.otus.lesson15.cache.model.CacheData;
import ru.zhelnin.otus.lesson15.message.Address;
import ru.zhelnin.otus.lesson15.message.Addressee;

public class CacheServiceImpl implements CacheService, Addressee {

    private final ZCache cache;

    private final MessageSystemContext messageSystemContext;

    public CacheServiceImpl(ZCache cache, MessageSystemContext messageSystemContext) {
        this.cache = cache;
        this.messageSystemContext = messageSystemContext;
    }

    public void init() {
        messageSystemContext.getMessageSystem().addAddressee(this);
    }

    @Override
    public CacheData getCacheData() {
        return new CacheData(cache.getMaxSize(), cache.getCurrentSize(), cache.getHitsCounter(), cache.getMissesCounter());
    }

    @Override
    public Address getAddress() {
        return messageSystemContext.getCacheAddress();
    }
}
