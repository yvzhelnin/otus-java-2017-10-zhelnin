package ru.zhelnin.otus.lesson2.structures;

import ru.zhelnin.otus.lesson2.measurement.MemoryMeter;
import ru.zhelnin.otus.lesson2.util.CliHandler;
import ru.zhelnin.otus.lesson2.util.ObjectUtil;

import java.util.ArrayList;
import java.util.Collection;

public class StructuresMaker {

    private static final int QUANTITY = 3_000_000;
    private static final int MAX_STRUCTURE_SIZE = 10;

    public static void fillAndMeasure(StructureType structureType) {
        CliHandler.printStartNotification();
        handleLoop(structureType);
        CliHandler.printEndNotification();
    }

    private static void handleLoop(StructureType structureType) {
        for (int structureSize = 0; structureSize <= MAX_STRUCTURE_SIZE; structureSize++) {
            final long memoryOnStart = fillIterationAndGetMemoryOnStart(structureType, structureSize);
            CliHandler.printStructureSize(MemoryMeter.countStructureSize(MemoryMeter.measureMemory() - memoryOnStart, QUANTITY), structureType, structureSize);
        }
    }

    private static long fillIterationAndGetMemoryOnStart(StructureType structureType, int structureSize) {
        Collection<Object> container = new ArrayList<>(QUANTITY);
        MemoryMeter.cleanMemory();
        final long memoryOnStart = MemoryMeter.measureMemory();
        for (int i = 0; i < QUANTITY; i++) {
            if (structureType.equals(StructureType.ARRAY)) {
                container.add(new Object[structureSize]);
            } else if (structureType.equals(StructureType.STRING)) {
                container.add(ObjectUtil.assembleString(structureSize));
            } else if (structureType.equals(StructureType.OBJECT)) {
                container.add(ObjectUtil.assembleObject(structureSize));
            } else if (structureType.equals(StructureType.ARRAY_LIST)) {
                container.add(new ArrayList<>(structureSize));
            }
        }
        return memoryOnStart;
    }
}
