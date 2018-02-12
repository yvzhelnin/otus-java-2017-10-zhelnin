package ru.zhelnin.otus.lesson14.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.zhelnin.otus.lesson14.util.RandomArrayGenerator;

import java.util.Arrays;

public class ConcurrentSorterTest {

    private ConcurrentSorter sorter;

    @Before
    public void makeSorter() {

    }

    @Test
    public void lowLevelSortTest() throws InterruptedException {
        sorter = new ConcurrentSorter(RandomArrayGenerator.generate(100));
        int[] source = sorter.getSource();
        Arrays.parallelSort(source);
        int[] sorted = sorter.sort(false);

        Assert.assertArrayEquals(source, sorted);
    }

    @Test
    public void executorSortTest() throws InterruptedException {
        sorter = new ConcurrentSorter(RandomArrayGenerator.generate(100));
        int[] source = sorter.getSource();
        Arrays.parallelSort(source);
        int[] sorted = sorter.sort(true);

        Assert.assertArrayEquals(source, sorted);
    }
}
