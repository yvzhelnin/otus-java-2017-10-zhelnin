package ru.zhelnin.otus.lesson14.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayDividerTest {

    private static final int[] testArray = {1, 2, 6, 3, 10000, 500, 59, 700, 2000000, 12, 0, 167, 80, 89, 670};

    @Test
    public void divideTest() {
        ArrayDivider divider = new ArrayDivider(testArray, 4);
        divider.divide();

        final int[] expectedFirst = {1, 2, 6};
        final int[] expectedSecond = {3, 10000, 500};
        final int[] expectedThird = {59, 700, 2000000};
        final int[] expectedForth = {12, 0, 167, 80, 89, 670};

        Assert.assertArrayEquals(expectedFirst, divider.getSubArrays()[0]);
        Assert.assertArrayEquals(expectedSecond, divider.getSubArrays()[1]);
        Assert.assertArrayEquals(expectedThird, divider.getSubArrays()[2]);
        Assert.assertArrayEquals(expectedForth, divider.getSubArrays()[3]);
    }

    @Test
    public void assembleBackTest() {
        ArrayDivider divider = new ArrayDivider(testArray, 4);
        divider.divide();

        Assert.assertArrayEquals(testArray, divider.assembleBack());
    }
}
