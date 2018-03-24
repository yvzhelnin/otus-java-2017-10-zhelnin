package ru.zhelnin.otus.lesson16.core.message;

import ru.zhelnin.otus.lesson16.core.model.CacheData;

public class CacheDataMessage extends Message {

    private int maxCacheSize;

    private int currentCacheSize;

    private int hitsCount;

    private int missesCount;

    public CacheDataMessage(CacheData data, String address, int backPortNumber, int targetPortNumber) {
        super(CacheDataMessage.class, address, backPortNumber, targetPortNumber);
        this.maxCacheSize = data.getMaxCacheSize();
        this.currentCacheSize = data.getCurrentCacheSize();
        this.hitsCount = data.getHitsCount();
        this.missesCount = data.getMissesCount();
    }

    public CacheData makeCacheData() {
        return new CacheData(maxCacheSize, currentCacheSize, hitsCount, missesCount);
    }

    @Override
    public String toString() {
        return "CacheDataMessage{" +
                "maxCacheSize=" + maxCacheSize +
                ", currentCacheSize=" + currentCacheSize +
                ", hitsCount=" + hitsCount +
                ", missesCount=" + missesCount +
                '}';
    }
}
