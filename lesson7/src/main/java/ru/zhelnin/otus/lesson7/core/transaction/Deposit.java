package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class Deposit extends Transaction {

    private final Atm targetAtm;

    private final int amountRequested;

    public Deposit(int amountRequested, Atm targetAtm) {
        super(Type.DEPOSIT);
        this.amountRequested = amountRequested;
        this.targetAtm = targetAtm;
    }

    public int deposit() {
        return targetAtm.getAccount().deposit(amountRequested);
    }
}
