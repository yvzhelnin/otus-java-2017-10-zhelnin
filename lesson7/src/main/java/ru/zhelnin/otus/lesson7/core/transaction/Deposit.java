package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class Deposit extends Transaction {

    private static final Atm ATM = Atm.getInstance();

    private final int amountRequested;

    public Deposit(int amountRequested) {
        super(Type.DEPOSIT);
        this.amountRequested = amountRequested;
    }

    public int deposit() {
        return ATM.getAccount().deposit(amountRequested);
    }
}
