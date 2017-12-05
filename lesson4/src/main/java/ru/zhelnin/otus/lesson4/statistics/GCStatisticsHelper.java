package ru.zhelnin.otus.lesson4.statistics;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class GCStatisticsHelper {

    private static String gcName;
    private static volatile long collectionsCount = 0L;
    private static volatile long time = 0L;

    public static void updateStatistics() {
        collectStatistics();
    }

    private static void collectStatistics() {
        for (GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            ((NotificationEmitter) gcBean).addNotificationListener((notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo gcInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    gcName = gcInfo.getGcName();
                    collectionsCount++;
                    time += gcInfo.getGcInfo().getDuration();
                }
            }, null, null);
        }
    }

    static String getGcName() {
        return gcName;
    }

    static long getCollectionsCount() {
        return collectionsCount;
    }

    static long getTime() {
        return time / collectionsCount;
    }

    static void resetStatistics() {
        collectionsCount = 0L;
        time = 0L;
    }
}
