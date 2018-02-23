package ru.zhelnin.otus.lesson15.generator.utils;

import java.util.Random;

class Randomizer {

    private static final int WORD_SIZE = 5;
    private static final String STREET_SIGN = " .str";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";

    static String generateStreet() {
        return generateString(ALPHABET) + STREET_SIGN;
    }

    static String generatePhone() {
        return generateString(NUMBERS);
    }

    static String generateName() {
        return generateString(ALPHABET);
    }

    private static String generateString(String sourceChars) {
        char[] text = new char[WORD_SIZE];
        for (int i = 0; i < WORD_SIZE; i++) {
            text[i] = sourceChars.charAt(new Random().nextInt(sourceChars.length()));
        }
        return new String(text);
    }
}
