package ru.zhelnin.otus.lesson5.experimental.test;

import ru.zhelnin.otus.lesson5.core.annotation.After;
import ru.zhelnin.otus.lesson5.core.annotation.Before;
import ru.zhelnin.otus.lesson5.core.annotation.Test;
import ru.zhelnin.otus.lesson5.core.assertion.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperimentalTwo {

    private List<Integer> testElements = new ArrayList<>();

    @Before
    public void beforeTest() {
        testElements.add(1);
        testElements.add(2);
        testElements.add(3);
    }

    @After
    public void afterTest() {
        testElements = new ArrayList<>();
    }

    @Test
    public void firstTest() {
        testElements.remove(0);

        Assert.assertLists(Arrays.asList(2, 3), testElements);
    }

    @Test
    public void secondTest() {
        testElements.remove(1);

        Assert.assertLists(Arrays.asList(1, 3), testElements);
    }
}
