package ru.zhelnin.otus.lesson7.console;

import ru.zhelnin.otus.lesson7.console.menu.MenuFactory;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.core.transaction.TransactionType;
import ru.zhelnin.otus.lesson7.util.EnumHelper;

import java.io.Console;
import java.util.Collection;

public class ConsoleHandler {

    private static final Collection<String> AVAILABLE_OPERATIONS = new EnumHelper<TransactionType, String>().getAvailableValues(TransactionType.class, TransactionType::getCode);

    public static void execute(Console console) {
        if (console != null) {
            String selected = handle(console, ConsoleConstants.WELCOME_MENU, ConsoleConstants.WELCOME_INSTRUCTION);
            if (!AVAILABLE_OPERATIONS.contains(selected)) {
                execute(console);
            } else {
                showSelectedMenu(console, selected);
            }
        }
    }

    private static void showSelectedMenu(Console console, String selectedCode) {
        Menu selectedMenu = new MenuFactory(console).getMenu(TransactionType.getByCode(selectedCode));
        if (selectedMenu != null) {
            selectedMenu.handleMenu();
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

    public static void printBalance(int balance, Console console) {
        System.out.println("\nTransaction successfully completed. You have " + balance + " rubles on your account.\n");
        execute(console);
    }
}
