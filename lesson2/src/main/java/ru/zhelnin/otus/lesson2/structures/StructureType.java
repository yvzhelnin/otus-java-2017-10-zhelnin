package ru.zhelnin.otus.lesson2.structures;

public enum StructureType {

    UNKNOWN(0, ""),
    ARRAY(1, "Array"),
    STRING(2, "String"),
    OBJECT(3, "Object"),
    ARRAY_LIST(4, "ArrayList");

    private final int value;
    private final String name;

    StructureType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int searchValue) {
        for (StructureType type : values()) {
            if (type.getValue() == searchValue) {
                return type.getName();
            }
        }
        return UNKNOWN.getName();
    }

    public static StructureType getById(int searchValue) {
        for (StructureType type : values()) {
            if (type.getValue() == searchValue) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
