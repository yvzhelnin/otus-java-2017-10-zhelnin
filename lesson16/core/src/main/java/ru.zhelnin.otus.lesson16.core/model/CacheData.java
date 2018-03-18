package ru.zhelnin.otus.lesson16.core.model;

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

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public int getCurrentCacheSize() {
        return currentCacheSize;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public int getMissesCount() {
        return missesCount;
    }
}
