package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;

public class Deposit {

    private static final Atm ATM = Atm.getInstance();

    private final int amountRequested;

    public Deposit(int amountRequested) {
        this.amountRequested = amountRequested;
    }

    public int deposit() {
        return ATM.getAccount().deposit(amountRequested);
    }
}
