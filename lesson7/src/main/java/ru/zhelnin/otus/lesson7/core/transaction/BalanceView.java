package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;

public class BalanceView {

    private static final Atm ATM = Atm.getInstance();

    public static int view() {
        return ATM.getAccount().getBalance();
    }
}
