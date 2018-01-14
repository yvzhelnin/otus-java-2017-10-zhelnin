package ru.zhelnin.otus.lesson11.main;

import ru.zhelnin.otus.lesson11.cache.ZCache;
import ru.zhelnin.otus.lesson11.cache.ZCacheImpl;

public class Main {

    public static void main(String[] args) {
        ZCache<Integer, String> cache = new ZCacheImpl<>(3, 5);
        cache.putElement(1, "1Element");
        cache.putElement(2, "2Element");
        cache.putElement(3, "3Element");
        cache.putElement(4, "4Element");
        cache.putElement(5, "5Element");
        cache.putElement(6, "6Element");

        System.out.println(cache.toString());

        System.out.println("\nTrying to get elements from cache:");

        System.out.println("Key = 1: " + cache.getElement(1));
        System.out.println("Key = 2: " + cache.getElement(2));
        System.out.println("Key = 4: " + cache.getElement(4));
        System.out.println("Key = 5: " + cache.getElement(5));
        System.out.println("Key = 6: " + cache.getElement(6));

        System.out.println("\nInserting a new element with evicting an unclaimed one (number 3):");

        cache.putElement(7, "7Element");

        System.out.println(cache.toString());

        System.out.println("\nTrying to get all the elements:");

        cache.getAllElements().forEach(System.out::println);
    }
}
