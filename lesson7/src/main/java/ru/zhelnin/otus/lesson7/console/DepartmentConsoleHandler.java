package ru.zhelnin.otus.lesson7.console;

import ru.zhelnin.otus.lesson7.console.menu.observer.Event;
import ru.zhelnin.otus.lesson7.console.menu.observer.Observer;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentConsoleHandler extends ConsoleHandler {

    public static final String PRINT_NOTES = "1";
    public static final String RESTORE_ATMS = "2";
    private static final String ENTER_CONCRETE = "3";

    private static final List<String> AVAILABLE_OPERATIONS = Arrays.asList(PRINT_NOTES, RESTORE_ATMS, ENTER_CONCRETE);

    private static final List<Observer> OBSERVERS = new ArrayList<>();


    public static void execute(Console console, AtmDepartment department) throws NoSuchDenominationException {
        if (console != null) {
            String selected = handle(console, ConsoleConstants.DEPARTMENT_MENU, ConsoleConstants.DEFAULT_INSTRUCTION);
            if (!AVAILABLE_OPERATIONS.contains(selected)) {
                execute(console, department);
            } else {
                makeAction(console, selected, department);
            }
        }
    }

    private static void makeAction(Console console, String selectedCode, AtmDepartment department) throws NoSuchDenominationException {
        switch (selectedCode) {
            case PRINT_NOTES:
                notify(new Event(department), PRINT_NOTES);
                execute(console, department);
                break;
            case RESTORE_ATMS:
                notify(new Event(department), RESTORE_ATMS);
                execute(console, department);
            case ENTER_CONCRETE:
                enterConcreteAtm(console, department);
        }
    }

    private static void enterConcreteAtm(Console console, AtmDepartment department) throws NoSuchDenominationException {
        ConsoleHandler.execute(console, department.getConcreteAtm(parseAtmNumber(console, department)));
    }

    private static int parseAtmNumber(Console console, AtmDepartment department) throws NoSuchDenominationException {
        Integer selectedNumber = -1;
        try {
            selectedNumber = Integer.valueOf(handle(console, ConsoleConstants.EMPTY_STRING, ConsoleConstants.CONCRETE_ATM_CHOOSING_INSTRUCTION));
            if (selectedNumber < 0 || selectedNumber > 7) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println(ConsoleConstants.WRONG_DATA);
            execute(console, department);
        }
        return selectedNumber;
    }

    public static void register(Observer observer) {
        OBSERVERS.add(observer);
    }

    private static void notify(Event event, String type) {
        OBSERVERS.forEach(observer -> observer.notify(event, type));
    }
}
