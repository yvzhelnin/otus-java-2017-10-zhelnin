package ru.zhelnin.otus.lesson12.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ZCacheImpl<K, V> implements ZCache<K, V> {

    private static final int DEFAULT_MAX_SIZE = 100;

    private final int initialSize;
    private final int maxSize;

    private int hitsCounter = 0;
    private int missesCounter = 0;

    private final Map<K, CachedElement<K, V>> internalCache;

    public ZCacheImpl(int initialSize, int maxSize) {
        this.initialSize = initialSize;
        this.maxSize = maxSize > 0 ? maxSize : DEFAULT_MAX_SIZE;

        internalCache = initialSize > 0 ? new HashMap<>(initialSize) : new HashMap<>();
    }

    public V getElement(K key) {
        return internalCache.get(key) == null ? handleMiss() : handleHit(internalCache.get(key)).getValue();
    }

    private V handleMiss() {
        missesCounter++;

        return null;
    }

    private CachedElement<K, V> handleHit(CachedElement<K, V> returningElement) {
        returningElement.stampLastRequestTime();
        hitsCounter++;

        return returningElement;
    }

    public void putElement(K key, V value) {
        if (internalCache.size() < maxSize) {
            internalCache.put(key, new CachedElement<>(key, value));
        } else {
            evictUnclaimedElement();
            internalCache.put(key, new CachedElement<>(key, value));
        }
    }

    private void evictUnclaimedElement() {
        internalCache.remove(findUnclaimedElement());
    }

    private K findUnclaimedElement() {
        long minRequestTime = System.currentTimeMillis();
        CachedElement<K, V> unclaimedElement = null;
        for (Map.Entry<K, CachedElement<K, V>> entry : internalCache.entrySet()) {
            if (entry.getValue().getLastRequestTime() < minRequestTime) {
                unclaimedElement = entry.getValue();
                minRequestTime = unclaimedElement.getLastRequestTime();
            }
        }
        return unclaimedElement == null ? null : unclaimedElement.getKey();
    }

    public List<V> getAllElements() {
        hitsCounter += internalCache.size();
        return internalCache.values().stream().map(CachedElement::getValue).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ZCacheImpl{" +
                "initialSize=" + initialSize +
                ", maxSize=" + maxSize +
                ", hitsCounter=" + hitsCounter +
                ", missesCounter=" + missesCounter +
                ", internalCache=\n" + internalCache.values().stream().map(CachedElement::toString).collect(Collectors.joining("\n")) +
                '}';
    }
}
