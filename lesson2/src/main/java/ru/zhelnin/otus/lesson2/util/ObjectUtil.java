package ru.zhelnin.otus.lesson2.util;

import ru.zhelnin.otus.lesson2.structures.object.Experimental;

public class ObjectUtil {

    public static String assembleString(int stringSize) {
        if (stringSize == 0) {
            return new String(new char[0]);
        } else {
            char[] result = new char[stringSize];
            for (int i = 0; i < stringSize; i++) {
                result[i] = 'a';
            }
            return new String(result);
        }
    }

    public static Object assembleObject(int size) {
        return size == 0 ? new Experimental() : new Experimental(new int[size]);
    }
}
