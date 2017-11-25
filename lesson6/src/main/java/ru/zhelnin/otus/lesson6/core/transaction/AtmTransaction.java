package ru.zhelnin.otus.lesson6.core.transaction;

import ru.zhelnin.otus.lesson6.core.atm.Atm;

public abstract class AtmTransaction {

    protected final static Atm ATM = Atm.getInstance();

    public abstract int execute();
}
