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
        ParseCmdParameters cmdParser = new ParseCmdParameters();
        try {
            cmdParameters = cmdParser.parse(args);
        } catch (ParseException ex) {
            log.error("Error parsing command-line arguments: " + ex.getMessage());
            cmdParser.printHelp();
        }

        FigureParameters figureParameters = null;
        try {
            figureParameters = FigureParameters.readParametersFromFile(cmdParameters.filePath());
        } catch (IllegalArgumentException ex) {
            log.error("Error: " + ex.getMessage());
        } catch (IOException ex) {
            log.error("File reading error: " + ex.getMessage());
        }
        if (figureParameters == null) {
            log.error("Failed to load figure parameters. Stopping execution.");
        }

        Figure figure = null;
        try{
            figure = FigureFactory.createFigure(figureParameters);
        } catch(IllegalArgumentException ex){
            log.error("Error: " + ex.getMessage());
        }

        if (figure != null) {
            String figureInfo = figure.figureInfo();
            try {
                FigureInfoWriter writer = new FigureInfoWriter(cmdParameters);
                writer.write(figureInfo);
            } catch (IOException ex) {
                log.error("Error writing figure info: " + ex.getMessage());
            }
        }
        log.info("The program completed.");
    }
}