package ru.zhelnin.otus.lesson5.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

class ResultHandler {

    private static final String FAILED = "\nAll the tests failed!";
    private static final String PASSED = "\nAll the tests passed!";

    private Collection<String> passed;
    private Collection<String> failed;

    ResultHandler() {
        passed = new ArrayList<>();
        failed = new ArrayList<>();
    }

    void addPassed(String passedName) {
        passed.add(passedName);
    }

    void addFailed(String failedName) {
        failed.add(failedName);
    }

    void printResult(Collection<Method> methods) {
        if (failed.size() == methods.size()) {
            System.out.println(FAILED);
        } else if (passed.size() == methods.size()) {
            System.out.println(PASSED);
        } else {
            System.out.println("\nResult:\n" + passed.size() + " tests passed; " + failed.size() + " tests failed.\n");
            System.out.println("Failed tests:\n");
            failed.forEach(System.out::println);
        }
    }
}
