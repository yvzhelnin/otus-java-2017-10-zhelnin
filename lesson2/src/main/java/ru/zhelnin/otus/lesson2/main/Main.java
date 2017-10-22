package ru.zhelnin.otus.lesson2.main;

import ru.zhelnin.otus.lesson2.structures.StructureType;
import ru.zhelnin.otus.lesson2.structures.StructuresMaker;
import ru.zhelnin.otus.lesson2.util.CliHandler;

public class Main {

    public static void main(String[] args) {
        int structureType = CliHandler.getStructureType(args);
        if (structureType != StructureType.UNKNOWN.getValue()) {
            StructuresMaker.fillAndMeasure(structureType);
        }
    }
}
