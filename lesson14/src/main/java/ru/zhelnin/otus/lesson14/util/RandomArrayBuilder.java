package ru.zhelnin.otus.lesson14.util;

import java.util.Random;

public class RandomArrayBuilder {

    private static final int SIZE = 15;

    public static int[] generate() {
        int[] result = new int[SIZE];
        for (int i = 0; i < SIZE - 1; i++) {
            result[i] = new Random().nextInt();
        }
        return result;
    }
}
