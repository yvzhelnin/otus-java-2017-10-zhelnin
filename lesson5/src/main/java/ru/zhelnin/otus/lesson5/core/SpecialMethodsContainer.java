package ru.zhelnin.otus.lesson5.core;

import java.lang.reflect.Method;

class SpecialMethodsContainer {

    private final Method beforeMethod;
    private final Method afterMethod;

    SpecialMethodsContainer(Method beforeMethod, Method afterMethod) {
        this.beforeMethod = beforeMethod;
        this.afterMethod = afterMethod;
    }

    Method getBeforeMethod() {
        return beforeMethod;
    }

    Method getAfterMethod() {
        return afterMethod;
    }
}
