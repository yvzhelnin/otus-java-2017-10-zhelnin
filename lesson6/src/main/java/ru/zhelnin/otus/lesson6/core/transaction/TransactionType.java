package ru.zhelnin.otus.lesson6.core.transaction;

import java.util.Objects;

public enum TransactionType {

    DEPOSIT("1"),
    BALANCE_VIEW("2"),
    WITHDRAWAL("3");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static TransactionType getByCode(String requestedCode) {
        for (TransactionType type : values()) {
            if (Objects.equals(type.getCode(), requestedCode)) {
                return type;
            }
        }
        return null;
    }
}
