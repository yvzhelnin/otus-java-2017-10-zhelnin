package ru.zhelnin.otus.lesson6.core.account;

public class Account {

    private static final int INITIAL_BALANCE = 100000;

    private int balance;

    public Account() {
        this.balance = INITIAL_BALANCE;
    }

    public int getBalance() {
        return balance;
    }

    public int deposit(int value) {
        balance += value;

        return balance;
    }

    public int withdraw(int value) {
        balance -= value;

        return balance;
    }
}
