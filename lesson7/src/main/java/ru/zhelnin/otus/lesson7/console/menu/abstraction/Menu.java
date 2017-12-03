package ru.zhelnin.otus.lesson7.console.menu.abstraction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.note.Denomination;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;
import ru.zhelnin.otus.lesson7.util.EnumHelper;

import java.io.Console;
import java.util.Collection;

public abstract class Menu {

    protected static final Collection<Integer> AVAILABLE_DENOMINATIONS = new EnumHelper<Denomination, Integer>().getAvailableValues(Denomination.class, Denomination::getValue);

    protected final Console console;

    protected final Atm atm;

    protected Menu(Console console, Atm atm) {
        this.console = console;
        this.atm = atm;
    }

    public abstract void handleMenu() throws NoSuchDenominationException;
}
