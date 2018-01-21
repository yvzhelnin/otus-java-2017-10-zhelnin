package ru.zhelnin.otus.lesson12.cache.model;

public class CacheData {

    private final int maxCacheSize;

    private final int currentCacheSize;

    private final int hitsCount;

    private final int missesCount;

    public CacheData(int maxCacheSize, int currentCacheSize, int hitsCount, int missesCount) {
        this.maxCacheSize = maxCacheSize;
        this.currentCacheSize = currentCacheSize;
        this.hitsCount = hitsCount;
        this.missesCount = missesCount;
    }
}
