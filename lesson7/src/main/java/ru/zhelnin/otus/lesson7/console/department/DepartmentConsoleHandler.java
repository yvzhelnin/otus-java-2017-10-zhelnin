package ru.zhelnin.otus.lesson7.console.department;

import ru.zhelnin.otus.lesson7.console.ConsoleConstants;
import ru.zhelnin.otus.lesson7.console.menu.observer.Event;
import ru.zhelnin.otus.lesson7.console.menu.observer.Observer;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentConsoleHandler extends ConsoleHandler {

    private static final Map<String, DepartmentConsoleCommand> commands = new HashMap<>();

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
        } else {
            System.out.println(ConsoleConstants.CONSOLE_IS_NULL);
        }
    }

    private static void makeAction(Console console, String selectedCode, AtmDepartment department) throws NoSuchDenominationException {
        fillCommands(console, department).get(selectedCode).execute();
    }

    private static Map<String, DepartmentConsoleCommand> fillCommands(Console console, AtmDepartment department) {
        if (commands.isEmpty()) {
            commands.put(PRINT_NOTES, new NotifyDepartmentCommand(department, console, PRINT_NOTES));
            commands.put(RESTORE_ATMS, new NotifyDepartmentCommand(department, console, RESTORE_ATMS));
            commands.put(ENTER_CONCRETE, new EnterConcreteAtmCommand(department, console));
        }
        return commands;
    }

    static void enterConcreteAtm(Console console, AtmDepartment department) throws NoSuchDenominationException {
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

    static void notify(Event event, String type) {
        OBSERVERS.forEach(observer -> observer.notify(event, type));
    }
}
