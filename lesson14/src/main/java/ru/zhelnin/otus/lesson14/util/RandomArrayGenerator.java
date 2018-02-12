package ru.zhelnin.otus.lesson14.util;

import java.util.Random;

public class RandomArrayGenerator {

    public static int[] generate(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size - 1; i++) {
            result[i] = new Random().nextInt();
        }
        return result;
    }
}
