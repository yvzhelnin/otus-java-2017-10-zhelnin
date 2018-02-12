package ru.zhelnin.otus.lesson14.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.zhelnin.otus.lesson14.util.RandomArrayGenerator;

import java.util.Arrays;

public class ConcurrentSorterTest {

    private int[] source;
    private ConcurrentSorter sorter;

    @Before
    public void makeSorter() {
        source = RandomArrayGenerator.generate(10000);
        sorter = new ConcurrentSorter(source);
    }

    @Test
    public void lowLevelSortTest() throws InterruptedException {
        Arrays.parallelSort(source);
        int[] sorted = sorter.sort(false);

        Assert.assertArrayEquals(source, sorted);
    }

    @Test
    public void executorSortTest() throws InterruptedException {
        Arrays.parallelSort(source);
        int[] sorted = sorter.sort(true);

        Assert.assertArrayEquals(source, sorted);
    }
}
