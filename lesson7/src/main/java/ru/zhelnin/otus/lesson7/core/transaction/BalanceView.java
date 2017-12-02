package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class BalanceView extends Transaction {

    private static final Atm ATM = Atm.getInstance();

    public BalanceView() {
        super(Type.BALANCE_VIEW);
    }

    public Integer view() {
        return ATM.getAccount().getBalance();
    }
}
