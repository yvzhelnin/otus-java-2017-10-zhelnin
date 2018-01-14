package ru.zhelnin.otus.lesson11.cache;

import java.util.Collection;

public interface ZCache<K, V> {

    V getElement(K key);

    void putElement(K key, V value);

    Collection<V> getAllElements();
}
