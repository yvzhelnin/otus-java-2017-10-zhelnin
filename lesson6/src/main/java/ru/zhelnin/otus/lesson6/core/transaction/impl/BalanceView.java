package ru.zhelnin.otus.lesson6.core.transaction.impl;

import ru.zhelnin.otus.lesson6.core.atm.Atm;
import ru.zhelnin.otus.lesson6.core.transaction.AtmTransaction;

public class BalanceView extends AtmTransaction {

    public int execute() {
        return Atm.getInstance().getAccount().getBalance();
    }
}
