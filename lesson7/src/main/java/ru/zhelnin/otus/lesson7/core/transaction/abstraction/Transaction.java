package ru.zhelnin.otus.lesson7.core.transaction.abstraction;

public class Transaction {

    public enum Type {
        DEPOSIT("1"),
        BALANCE_VIEW("2"),
        WITHDRAWAL("3");

        private String code;

        Type(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static Type getByCode(String targetCode) {
            for (Type type : values()) {
                if (type.getCode().equals(targetCode)) {
                    return type;
                }
            }
            return null;
        }
    }

    private Type type;

    public Transaction(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
