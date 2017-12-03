package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class BalanceView extends Transaction {

    private final Atm targetAtm;

    public BalanceView(Atm targetAtm) {
        super(Type.BALANCE_VIEW);
        this.targetAtm = targetAtm;
    }

    public Integer view() {
        return targetAtm.getAccount().getBalance();
    }
}
