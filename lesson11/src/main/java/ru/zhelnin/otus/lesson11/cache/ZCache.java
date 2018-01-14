package ru.zhelnin.otus.lesson11.cache;

public interface ZCache<K, V> {

    V getElement(K key);

    void putElement(K key, V value);
}
