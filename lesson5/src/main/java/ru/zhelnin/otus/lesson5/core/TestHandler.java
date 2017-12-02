package ru.zhelnin.otus.lesson5.core;

import ru.zhelnin.otus.lesson5.core.annotation.After;
import ru.zhelnin.otus.lesson5.core.annotation.Before;
import ru.zhelnin.otus.lesson5.core.annotation.Test;
import ru.zhelnin.otus.lesson5.core.util.ReflectionHelper;
import ru.zhelnin.otus.lesson5.core.util.TestException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestHandler {

    public static final String TOO_MANY_SPECIAL_METHODS = "\nError! There are too many @Before or @After methods in tested class";

    @SuppressWarnings("SameParameterValue")
    public static void executeTests(String packageName) throws InvocationTargetException {
        for (Class currentClass : ReflectionHelper.findClasses(packageName)) {
            executeTests(currentClass);
        }
    }

    public static void executeTests(Class<?> clazz, Object... args) throws InvocationTargetException {
        printStart(clazz);
        new RunHandler(getSpecialMethod(clazz, Before.class),
                ReflectionHelper.getMethodsByAnnotation(clazz, Test.class),
                getSpecialMethod(clazz, After.class))
                .runTests(clazz, args);
    }

    private static void printStart(Class<?> clazz) {
        System.out.println("\nExecuting tests in class " + clazz.getName());
    }

    private static Method getSpecialMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        Method foundOne = null;
        try {
            foundOne = ReflectionHelper.getAnnotatedMethod(clazz, annotation);
        } catch (TestException e) {
            System.out.println(TOO_MANY_SPECIAL_METHODS);
        }
        return foundOne;
    }
}
