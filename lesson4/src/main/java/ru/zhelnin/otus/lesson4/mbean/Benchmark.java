package ru.zhelnin.otus.lesson4.mbean;

import ru.zhelnin.otus.lesson4.statistics.CollectionsTask;
import ru.zhelnin.otus.lesson4.statistics.GCStatisticsHelper;

import java.util.Date;
import java.util.Timer;

public class Benchmark implements BenchmarkMBean {

    private static final long MILLIS_IN_MINUTE_AMOUNT = 60000L;

    private volatile int size = 0;

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        createTimer();
        System.out.println("Starting the loop");
        while (true) {
            int local = size;
            Object[] array = new Object[local];
            System.out.println("Array of size: " + array.length + " created");
            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
            }
            System.out.println("Created " + local + " objects.");
            GCStatisticsHelper.updateStatistics();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    private void createTimer() {
        Timer refreshStatisticsTimer = new Timer();
        refreshStatisticsTimer.schedule(new CollectionsTask(), new Date(new Date().getTime() + MILLIS_IN_MINUTE_AMOUNT), MILLIS_IN_MINUTE_AMOUNT);
    }
}
