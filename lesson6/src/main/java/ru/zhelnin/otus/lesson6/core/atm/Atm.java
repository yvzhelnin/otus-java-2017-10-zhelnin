package ru.zhelnin.otus.lesson6.core.atm;

import ru.zhelnin.otus.lesson6.core.account.Account;

public class Atm {

    private static final Atm instance = new Atm();

    private final Account account;

    private Atm() {
        account = new Account();
    }

    public static Atm getInstance() {
        return instance;
    }

    public Account getAccount() {
        return account;
    }
}
