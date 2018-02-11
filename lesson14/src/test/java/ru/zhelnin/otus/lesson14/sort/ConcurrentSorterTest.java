package ru.zhelnin.otus.lesson14.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ConcurrentSorterTest {

    @Test
    public void lowLevelSortTest() throws InterruptedException {
        int[] source = ConcurrentSorter.getSource();
        Arrays.parallelSort(source);
        int[] sorted = ConcurrentSorter.sort(false);

        Assert.assertArrayEquals(source, sorted);
    }

    @Test
    public void executorSortTest() throws InterruptedException {
        int[] source = ConcurrentSorter.getSource();
        Arrays.parallelSort(source);
        int[] sorted = ConcurrentSorter.sort(true);

        Assert.assertArrayEquals(source, sorted);
    }
}
