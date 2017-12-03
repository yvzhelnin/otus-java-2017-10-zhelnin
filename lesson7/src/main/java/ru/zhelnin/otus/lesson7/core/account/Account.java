package ru.zhelnin.otus.lesson7.core.account;

public class Account {

    private static final Account instance = new Account();

    private static final int INITIAL_BALANCE = 100000;

    private Integer balance;

    private Account() {
        this.balance = INITIAL_BALANCE;
    }

    public static Account getInstance() {
        return instance;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer deposit(int value) {
        balance += value;

        return balance;
    }

    public Integer withdraw(Integer value) {
        balance -= value;

        return balance;
    }
}
