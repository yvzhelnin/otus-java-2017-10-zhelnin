package ru.zhelnin.otus.lesson5.main;

import ru.zhelnin.otus.lesson5.core.TestHandler;
import ru.zhelnin.otus.lesson5.experimental.ExperimentalOne;
import ru.zhelnin.otus.lesson5.experimental.TooManyBeforeMethodsTest;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException {
        TestHandler.executeTests(ExperimentalOne.class);
        TestHandler.executeTests(TooManyBeforeMethodsTest.class);
        TestHandler.executeTests("ru.zhelnin.otus.lesson5.experimental.test");
    }
}
