package ru.zhelnin.otus.lesson5.core;

import ru.zhelnin.otus.lesson5.core.util.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

class RunHandler {

    private final Method beforeMethod;
    private final Method afterMethod;

    private final Collection<Method> testMethods;

    RunHandler(Method beforeMethod, Collection<Method> testMethods, Method afterMethod) {
        this.beforeMethod = beforeMethod;
        this.testMethods = testMethods;
        this.afterMethod = afterMethod;
    }

    void runTests(Class<?> clazz, Object[] args)
            throws InvocationTargetException {
        ResultHandler resultHandler = new ResultHandler();
        for (Method test : testMethods) {
            makeTestIteration(test, resultHandler, clazz, args);
        }
        resultHandler.printResult(testMethods);
    }

    private void makeTestIteration(Method testMethod, ResultHandler resultHandler, Class<?> clazz, Object[] args)
            throws InvocationTargetException {
        Object instance = ReflectionHelper.instantiate(clazz, args);
        executeMethod(instance, beforeMethod);
        invokeTestMethod(testMethod, resultHandler, instance);
        executeMethod(instance, afterMethod);
    }

    private void executeMethod(Object instance, Method method) throws InvocationTargetException {
        if (method != null) {
            ReflectionHelper.callMethod(instance, method.getName());
        }
    }

    private void invokeTestMethod(Method testMethod, ResultHandler resultHandler, Object instance) {
        String testMethodName = testMethod.getName();
        try {
            ReflectionHelper.callMethod(instance, testMethodName);
            resultHandler.addPassed(testMethodName);
        } catch (InvocationTargetException e) {
            resultHandler.addFailed(testMethodName);
        }
    }
}
