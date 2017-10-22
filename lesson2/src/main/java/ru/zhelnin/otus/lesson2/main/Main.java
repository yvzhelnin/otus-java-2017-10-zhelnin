package ru.zhelnin.otus.lesson2.main;

import ru.zhelnin.otus.lesson2.structures.StructureType;
import ru.zhelnin.otus.lesson2.structures.StructuresMaker;
import ru.zhelnin.otus.lesson2.util.CliHandler;

public class Main {

    public static void main(String[] args) {
        StructureType structureType = StructureType.getById(CliHandler.getStructureType(args));
        if (structureType.getValue() != StructureType.UNKNOWN.getValue()) {
            StructuresMaker.fillAndMeasure(structureType);
        }
    }
}
