package ru.zhelnin.otus.lesson1;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) {
        Options options = new Options();

        Option input = new Option("is", "inputString", true, "input string to print (if there are space chars in the " +
                "string you have to use quotes)");
        input.setRequired(true);
        options.addOption(input);

        CommandLine commandLine = null;
        try {
            commandLine = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.out.println("Illegal argument!");
            new HelpFormatter().printHelp("utility-name", options);
            System.exit(1);
        }
        System.out.println(commandLine != null ? commandLine.getOptionValue("inputString") : "Error!");
    }
}
