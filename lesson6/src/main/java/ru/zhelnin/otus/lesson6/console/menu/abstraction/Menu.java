package ru.zhelnin.otus.lesson6.console.menu.abstraction;

import ru.zhelnin.otus.lesson6.note.Denomination;
import ru.zhelnin.otus.lesson6.util.EnumHelper;

import java.io.Console;
import java.util.Collection;

public abstract class Menu {

    protected static final Collection<Integer> AVAILABLE_DENOMINATIONS = new EnumHelper<Denomination, Integer>().getAvailableValues(Denomination.class, Denomination::getValue);

    protected final Console console;

    protected Menu(Console console) {
        this.console = console;
    }

    public abstract void handleMenu();
}
