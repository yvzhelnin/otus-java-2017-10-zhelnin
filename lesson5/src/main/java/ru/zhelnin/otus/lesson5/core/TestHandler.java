package ru.zhelnin.otus.lesson5.core;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.zhelnin.otus.lesson5.core.annotation.After;
import ru.zhelnin.otus.lesson5.core.annotation.Before;
import ru.zhelnin.otus.lesson5.core.annotation.Test;
import ru.zhelnin.otus.lesson5.core.util.ReflectionHelper;
import ru.zhelnin.otus.lesson5.core.util.TestException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestHandler {

    private static final String TOO_MANY_SPECIAL_METHODS = "\nError! There are too many @Before or @After methods in tested class";

    public static void executeTests(String packageName) throws InvocationTargetException {
        for (Class currentClass : findClasses(packageName)) {
            executeTests(currentClass);
        }
    }

    private static Collection<Class<?>> findClasses(String packageName) {
        return new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(formClassLoaderList().toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))))
                .getSubTypesOf(Object.class);
    }

    private static List<ClassLoader> formClassLoaderList() {
        List<ClassLoader> classLoadersList = new ArrayList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        return classLoadersList;
    }

    public static void executeTests(Class<?> clazz, Object... args) throws InvocationTargetException {
        printStart(clazz);
        SpecialMethodsContainer specialMethods = new SpecialMethodsContainer(getSpecialMethod(clazz, Before.class), getSpecialMethod(clazz, After.class));
        Object instance = ReflectionHelper.instantiate(clazz, args);
        runTests(specialMethods, getMethodsByAnnotation(clazz, Test.class), instance);
    }

    private static void printStart(Class<?> clazz) {
        System.out.println("\nExecuting tests in class " + clazz.getName());
    }

    private static Method getSpecialMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        Method foundOne = null;
        try {
            foundOne = getAnnotatedMethod(clazz, annotation);
        } catch (TestException e) {
            System.out.println(TOO_MANY_SPECIAL_METHODS);
        }
        return foundOne;
    }

    private static Method getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) throws TestException {
        List<Method> methods = getMethodsByAnnotation(clazz, annotation);
        switch (methods.size()) {
            case 0:
                return null;
            case 1:
                return methods.get(0);
            default:
                throw new TestException();
        }
    }

    private static void runTests(SpecialMethodsContainer specialMethods, Collection<Method> testMethods, Object instance)
            throws InvocationTargetException {
        ResultHandler resultHandler = new ResultHandler();
        for (Method test : testMethods) {
            makeTestIteration(test, resultHandler, specialMethods, instance);
        }
        resultHandler.printResult(testMethods);
    }

    private static void makeTestIteration(Method testMethod, ResultHandler resultHandler, SpecialMethodsContainer specialMethods, Object instance)
            throws InvocationTargetException {
        executeMethod(instance, specialMethods.getBeforeMethod());
        invokeTestMethod(testMethod, resultHandler, instance);
        executeMethod(instance, specialMethods.getAfterMethod());
    }

    private static <T> void executeMethod(T instance, Method method) throws InvocationTargetException {
        if (method != null) {
            ReflectionHelper.callMethod(instance, method.getName());
        }
    }

    private static void invokeTestMethod(Method testMethod, ResultHandler resultHandler, Object instance) {
        String testMethodName = testMethod.getName();
        try {
            ReflectionHelper.callMethod(instance, testMethodName);
            resultHandler.addPassed(testMethodName);
        } catch (InvocationTargetException e) {
            resultHandler.addFailed(testMethodName);
            e.printStackTrace();
        }
    }

    private static List<Method> getMethodsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods()).filter(e -> e.isAnnotationPresent(annotation)).collect(Collectors.toList());
    }
}
