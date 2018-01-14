package ru.zhelnin.otus.lesson11.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ZCacheImpl<K, V> implements ZCache<K, V> {

    private final int initialSize;
    private final int maxSize;

    private final Map<K, CachedElement<K, V>> internalCache;

    public ZCacheImpl(int initialSize, int maxSize) {
        this.initialSize = initialSize;
        this.maxSize = maxSize;

        internalCache = new HashMap<>(initialSize);
    }

    public V getElement(K key) {
        return internalCache.get(key) == null ? null : processReturningElement(internalCache.get(key)).getValue();
    }

    private CachedElement<K, V> processReturningElement(CachedElement<K, V> returningElement) {
        returningElement.stampLastRequestTime();

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

    @Override
    public String toString() {
        return internalCache.values().stream().map(CachedElement::toString).collect(Collectors.joining("\n"));
    }
}
