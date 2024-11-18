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

        ParseCmdParameters cmdParser = new ParseCmdParameters();

        try{
            CmdParameters cmdParameters = cmdParser.parse(args);

            FigureParameters figureParameters = FigureParameters.readParametersFromFile(cmdParameters.filePath());

            Figure figure = FigureFactory.createFigure(figureParameters);

            String figureInfo = figure.figureInfo();
            FigureInfoWriter figureInfoWriter = new FigureInfoWriter(cmdParameters);
            figureInfoWriter.write(figureInfo);
        } catch (ParseException ex){
            log.error("Error parsing command-line arguments: " + ex.getMessage());
            System.exit(1);
        } catch (IOException ex){
            log.error("Error: " + ex.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException ex){
            log.error("Error: " + ex.getMessage());
            System.exit(1);
        }

        log.info("The program completed.");
    }
}