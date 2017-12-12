package ru.zhelnin.otus.lesson8.main;

import ru.zhelnin.otus.lesson8.builder.ObjectReader;
import ru.zhelnin.otus.lesson8.experimental.TestObject;

import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        final int primitive = 1;
        final Integer[] array = {1, 2, 3, 4};
        final TestObject[] objectArray = {new TestObject(5, "firstObject", false), new TestObject(3, "secondObject", true)};
        final Collection<Integer> collection = Arrays.asList(array);

        System.out.println(ObjectReader.makeJson(primitive, "primitive"));
        System.out.println(ObjectReader.makeJson(array, "array"));
        System.out.println(ObjectReader.makeJson(collection, "collection"));
        System.out.println(ObjectReader.makeJson(new TestObject(5, "firstObject", false)));
        System.out.println(ObjectReader.makeJson(objectArray, "objectArray"));
        System.out.println(ObjectReader.makeJson(Arrays.asList(objectArray), "objectCollection"));
    }
}
