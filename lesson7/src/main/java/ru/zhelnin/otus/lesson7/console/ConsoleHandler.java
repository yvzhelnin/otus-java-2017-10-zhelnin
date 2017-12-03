package ru.zhelnin.otus.lesson7.console;

import ru.zhelnin.otus.lesson7.console.menu.MenuFactory;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;
import ru.zhelnin.otus.lesson7.util.EnumHelper;

import java.io.Console;
import java.util.Collection;

public class ConsoleHandler {

    private static final Collection<String> AVAILABLE_OPERATIONS = new EnumHelper<Transaction.Type, String>().getAvailableValues(Transaction.Type.class, Transaction.Type::getCode);

    public static void execute(Console console, Atm atm) throws NoSuchDenominationException {
        if (console != null) {
            String selected = handle(console, ConsoleConstants.WELCOME_MENU, ConsoleConstants.DEFAULT_INSTRUCTION);
            if (!AVAILABLE_OPERATIONS.contains(selected)) {
                execute(console, atm);
            } else {
                showSelectedMenu(console, selected, atm);
            }
        }
    }

    private static void showSelectedMenu(Console console, String selectedCode, Atm atm) throws NoSuchDenominationException {
        Menu selectedMenu = new MenuFactory(console, atm).getMenu(Transaction.Type.getByCode(selectedCode));
        if (selectedMenu != null) {
            selectedMenu.handleMenu();
        } else {
            DepartmentConsoleHandler.execute(console, atm.getDepartment());
        }
    }

    public static String handle(Console console, String message, String instruction) {
        printDialog(console, message, instruction);

        return console.readLine();
    }

    private static void printDialog(Console console, String message, String instruction) {
        System.out.println(message);
        console.format(instruction);
    }

    public static void printBalance(int balance, Console console, Atm atm) throws NoSuchDenominationException {
        System.out.println("\nTransaction successfully completed. You have " + balance + " rubles on your account.\n");
        execute(console, atm);
    }
}
