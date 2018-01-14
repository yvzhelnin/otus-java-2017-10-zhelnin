package ru.zhelnin.otus.lesson11.cache;

import java.lang.ref.SoftReference;

public class CachedElement<K, V> {

    private final K key;
    private final SoftReference<V> value;

    private long lastRequestTime = System.currentTimeMillis();

    CachedElement(K key, V rawValue) {
        this.key = key;
        this.value = new SoftReference<>(rawValue);
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value.get();
    }

    long getLastRequestTime() {
        return lastRequestTime;
    }

    void stampLastRequestTime() {
        this.lastRequestTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "CachedElement{" +
                "key=" + key +
                ", value=" + value.get() +
                '}';
    }
}
