package ru.zhelnin.otus.lesson2.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import ru.zhelnin.otus.lesson2.structures.StructureType;

public class CliHandler {

    private static final String DESCRIPTION = "input a number for type of data structure which size you want to measure:\n" +
            "1 - Array;\n" +
            "2 - String;\n" +
            "3 - Object\n" +
            "4 - ArrayList";

    private static final String START_NOTIFICATION = "Starting the loop";
    private static final String END_NOTIFICATION = "Measurement completed";

    public static void printStartNotification() {
        System.out.println(START_NOTIFICATION);
    }

    public static void printStructureSize(long memoryUsage, int structureType, int structureSize) {
        System.out.println("Size of " + StructureType.getNameByValue(structureType) + "(" + structureSize + ") is: " + memoryUsage + " bytes");
    }

    public static void printEndNotification() {
        System.out.println(END_NOTIFICATION);
    }

    public static int getStructureType(String[] args) {
        final Options options = makeOptions();
        CommandLine commandLine = null;
        try {
            commandLine = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            printHelp(options);
        }
        return checkForNullAndGetValue(commandLine);
    }

    private static void printHelp(Options options) {
        System.out.println("Illegal argument!");
        new HelpFormatter().printHelp("Memory meter:", options);
    }

    private static int checkForNullAndGetValue(CommandLine commandLine) {
        return commandLine != null ? Integer.valueOf(commandLine.getOptionValue("dataStructure")) : 0;
    }

    private static Options makeOptions() {
        Options options = new Options();
        Option dataStructure = new Option("ds", "dataStructure", true, DESCRIPTION);
        dataStructure.setRequired(true);
        options.addOption(dataStructure);

        return options;
    }
}
