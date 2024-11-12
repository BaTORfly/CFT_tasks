package ru.shift.parameters;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.Main;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ParseCmdParameters {
    private static final Logger log = LogManager.getLogger(ParseCmdParameters.class);

    public CmdParameters parse(String args[]) throws ParseException {
        Options options = new Options();

        Option inputFileOption = new Option("i", "input", true,
                "Input text file");
        inputFileOption.setRequired(true);
        options.addOption(inputFileOption);

        Option outputToConsoleOption = new Option("c", "console", false,
                "Output to console");
        outputToConsoleOption.setRequired(false);
        options.addOption(outputToConsoleOption);

        Option outputToFileOption = new Option("f", "file", false,
                "Output to file");
        outputToFileOption.setRequired(false);
        options.addOption(outputToFileOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (!(cmd.hasOption("c") ^ cmd.hasOption("f"))) {
            throw new ParseException("Either -c (console) or -f (file) must be provided, but not both.");
        }

        String filePath = null;
        try{
            URL resourceURL = Main.class.getClassLoader().getResource(cmd.getOptionValue("i"));
            if (resourceURL == null) {
                throw new FileNotFoundException("File " + cmd.getOptionValue("i") +
                        " Not found in resources.");
            }
            filePath = Paths.get(resourceURL.toURI()).toString();
        } catch(FileNotFoundException ex) {
            log.error("Error " + ex.getMessage());
        } catch (URISyntaxException ex) {
            log.error("Incorrect path file " + ex.getMessage());
        }

        CmdParameters cmdParameters = new CmdParameters(cmd.hasOption("c"), cmd.hasOption("f"), filePath);

        return cmdParameters;
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        Options options = new Options();

        options.addOption("c", "console", false, "Output to console");
        options.addOption("f", "file", false, "Output to file");
        options.addOption("i", "input", true, "Input text file");

        formatter.printHelp("figures utility", options);
    }
}