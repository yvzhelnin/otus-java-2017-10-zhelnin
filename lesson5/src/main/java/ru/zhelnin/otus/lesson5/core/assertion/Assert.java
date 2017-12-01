package ru.zhelnin.otus.lesson5.core.assertion;

import java.util.List;

public class Assert {

    public static <T> void assertLists(List<T> expected, List<T> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionError();
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                throw new AssertionError();
            }
        }
    }

    public static void assertTrue(boolean predicate) {
        if (!predicate) {
            throw new AssertionError();
        }
    }
}
