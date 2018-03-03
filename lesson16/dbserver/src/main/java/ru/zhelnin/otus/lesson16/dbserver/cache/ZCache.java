package ru.zhelnin.otus.lesson16.dbserver.cache;

import java.util.Collection;

public interface ZCache<K, V> {

    int getCurrentSize();

    int getMaxSize();

    int getHitsCounter();

    int getMissesCounter();

    V getElement(K key);

    void putElement(K key, V value);

    Collection<V> getAllElements();
}
