package ru.zhelnin.otus.lesson16.core.message;

public class CacheDataMessage extends Message {

    private int maxCacheSize;

    private int currentCacheSize;

    private int hitsCount;

    private int missesCount;

    public CacheDataMessage(String address) {
        super(CacheDataMessage.class, address);
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    public void setCurrentCacheSize(int currentCacheSize) {
        this.currentCacheSize = currentCacheSize;
    }

    public void setHitsCount(int hitsCount) {
        this.hitsCount = hitsCount;
    }

    public void setMissesCount(int missesCount) {
        this.missesCount = missesCount;
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
