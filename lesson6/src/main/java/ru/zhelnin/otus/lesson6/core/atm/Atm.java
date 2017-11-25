package ru.zhelnin.otus.lesson6.core.atm;

import ru.zhelnin.otus.lesson6.core.account.Account;

public class Atm {

    private static final Atm instance = new Atm();

    private final Cassete cassete;

    private final Account account;

    private Atm() {
        cassete = new Cassete();
        account = new Account();
    }

    public static Atm getInstance() {
        return instance;
    }

    public Cassete getCassete() {
        return cassete;
    }

    public Account getAccount() {
        return account;
    }
}
