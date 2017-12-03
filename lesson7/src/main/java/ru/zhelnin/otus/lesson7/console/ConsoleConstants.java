package ru.zhelnin.otus.lesson7.console;

public final class ConsoleConstants {

    static final String DEPARTMENT_MENU = "Welcome to Zhelnin Finance Inc. Atm Department!\n" +
            "Available operations:\n" +
            "1 - show notes amount over the department\n" +
            "2 - restore atms to their initial states\n" +
            "3 - enter a concrete ATM\n";
    public static final String WELCOME_MENU = "Welcome to Zhelnin Finance Inc. Atm!\n" +
            "Available operations:\n" +
            "1 - deposit money;\n" +
            "2 - view balance\n" +
            "3 - withdraw money\n" +
            "0 - return to department menu\n";
    public static final String DEPOSIT_MENU = "Welcome to Zhelnin Finance Inc. Atm!\n" +
            "Deposit. Available note denominations to cash in:\n" +
            "100\n" +
            "500\n" +
            "1000\n" +
            "5000\n";

    public static final String WITHDRAWAL_SUCCESS = "Please take money. You are getting next notes:\n";

    public static final String CONCRETE_ATM_CHOOSING_INSTRUCTION = "\nEnter ATM Number form 0 to 7 and press ENTER to proceed.\n";
    public static final String DEFAULT_INSTRUCTION = "\nChoose operation number and press ENTER to proceed.\n";
    public static final String DEPOSIT_DENOMINATION_INSTRUCTION = "\nChoose a denomination and press ENTER to proceed.\n";
    public static final String DEPOSIT_NOTES_INSTRUCTION = "\nEnter notes amount and press ENTER to proceed.\n";
    public static final String WITHDRAWAL_INSTRUCTION = "\nEnter how much money you want to withdraw and press ENTER to proceed.\n";

    public static final String EMPTY_STRING = "";

    public static final String WRONG_DATA = "Wrong value!\n";
    public static final String NO_SUCH_DENOMINATION = "It is impossible to issue the requested amount!\n";
}
