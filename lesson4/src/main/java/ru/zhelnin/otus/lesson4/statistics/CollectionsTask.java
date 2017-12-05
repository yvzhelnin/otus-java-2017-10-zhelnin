package ru.zhelnin.otus.lesson4.statistics;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

public class CollectionsTask extends TimerTask {

    private final static Logger logger = LogManager.getLogger(CollectionsTask.class);

    @Override
    public void run() {
        logStatistics();
    }

    private void logStatistics() {
        logger.log(Level.INFO,
                "GC name: " + GCStatisticsHelper.getGcName()
                        + "\nAverage time for one collection: " + GCStatisticsHelper.getCollectionsCount()
                        + "\nCollection count per minute: " + GCStatisticsHelper.getTime());
        resetCounters();
    }

    private void resetCounters() {
        GCStatisticsHelper.resetStatistics();
    }
}
