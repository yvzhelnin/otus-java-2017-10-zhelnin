package ru.zhelnin.otus.lesson5.experimental;

import ru.zhelnin.otus.lesson5.core.annotation.Before;
import ru.zhelnin.otus.lesson5.core.annotation.Test;
import ru.zhelnin.otus.lesson5.core.assertion.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExperimentalOne {

    private List<Integer> testElements;

    @Before
    public void beforeTest() {
        testElements = new ArrayList<>();
    }

    @Test
    public void goodTest() {
        testElements.add(1);
        testElements.add(2);
        testElements.add(3);
        testElements.add(4);
        testElements.add(5);

        Assert.assertLists(Arrays.asList(1, 2, 3, 4, 5), testElements);
    }

    @Test
    public void badTest() {
        Assert.assertTrue(testElements.size() == 5);
    }
}
