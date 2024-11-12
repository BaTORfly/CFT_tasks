package ru.shift;

import org.apache.commons.cli.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.shift.Exceptions.TriangleException;
import ru.shift.Factory.FigureFactory;
import ru.shift.figures.Figure;
import ru.shift.parameters.CmdParameters;
import ru.shift.parameters.FigureParameters;
import ru.shift.parameters.ParseCmdParameters;
import ru.shift.util.FigureInfoWriter;

import java.io.IOException;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws TriangleException {

        log.info("The program is running...");
        CmdParameters cmdParameters = null;
        try {
            ParseCmdParameters cmdParser = new ParseCmdParameters();
            cmdParameters = cmdParser.parse(args);
        } catch (ParseException ex) {
            log.error("Error parsing command-line arguments: " + ex.getMessage());
            new ParseCmdParameters().printHelp();
            System.exit(1);
        }

        FigureParameters figureParameters = null;
        try {
            figureParameters = FigureParameters.readParametersFromFile(cmdParameters.filePath());
        } catch (IOException ex) {
            log.error("File reading error: " + ex.getMessage());
            System.exit(1);
        } catch (NumberFormatException ex) {
            log.error("Error: incorrect number format in the file: " + ex.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException ex) {
            log.error("Error: " + ex.getMessage());
            System.exit(1);
        }

        Figure figure = FigureFactory.createFigure(figureParameters);

        if (figure != null) {
            String figureInfo = figure.figureInfo();
            try {
                FigureInfoWriter writer = new FigureInfoWriter(cmdParameters);
                writer.write(figureInfo);
            } catch (IOException ex) {
                log.error("Error writing figure info: " + ex.getMessage());
                System.exit(1);
            }
        }

        log.info("The program completed.");
    }
}