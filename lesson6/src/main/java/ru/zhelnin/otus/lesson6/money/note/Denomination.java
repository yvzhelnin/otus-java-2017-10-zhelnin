package ru.zhelnin.otus.lesson6.money.note;

public enum Denomination {

    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000),
    UNKNOWN(0);

    private final int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Denomination getNoteByValue(int requestedValue) {
        for (Denomination denomination : values()) {
            if (denomination.getValue() == requestedValue) {
                return denomination;
            }
        }
        return UNKNOWN;
    }
}
