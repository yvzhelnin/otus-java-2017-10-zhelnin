package ru.zhelnin.otus.lesson14.sort;

import ru.zhelnin.otus.lesson14.util.ArrayDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSorter {

    public static final int THREADS_COUNT = 4;

    private final ArrayDivider divider;
    private final int[] source;


    public ConcurrentSorter(int[] source) {
        this.source = source;
        divider = new ArrayDivider(this.source, THREADS_COUNT);
        divider.divide();
    }

    public int[] sort(boolean withExecutor) throws InterruptedException {
        if (withExecutor) {
            sortWithExecutor();
        } else {
            sortParts();
        }

        int[] united = divider.assembleBack();
        Arrays.sort(united);

        return united;
    }

    private void sortParts() throws InterruptedException {
        for (Thread currentThread : runSortingThreads()) {
            finishThread(currentThread);
        }
    }

    private Iterable<Thread> runSortingThreads() throws InterruptedException {
        int i = 1;
        Collection<Thread> threads = new ArrayList<>();
        for (int[] currentPart : divider.getSubArrays()) {
            threads.add(sortPart(currentPart, i++));
        }

        return threads;
    }

    private Thread sortPart(int[] part, int index) throws InterruptedException {
        final String threadName = "Sorting-thread-" + index;
        System.out.println("sort started, thread " + threadName);

        return runThread(new Thread(() -> Arrays.sort(part)), threadName);
    }

    private Thread runThread(Thread sortThread, String name) {
        sortThread.setName(name);
        sortThread.start();

        return sortThread;
    }

    private void finishThread(Thread thread) throws InterruptedException {
        thread.join();
        System.out.println("sort done, thread " + thread.getName());
    }

    private void sortWithExecutor() throws InterruptedException {
        sortPartsWithExecutor(Executors.newFixedThreadPool(THREADS_COUNT));
    }

    private void sortPartsWithExecutor(ExecutorService executor) throws InterruptedException {
        for (int[] currentPart : divider.getSubArrays()) {
            executeSortTask(executor, currentPart);
        }
    }

    private static void executeSortTask(ExecutorService executor, int[] part) {
        executor.execute(() -> Arrays.sort(part));
    }
}
