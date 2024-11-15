package ru.shift.parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.figures.FigureTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public record FigureParameters(FigureTypes type, String parameterLine) {

    private static final Logger log = LogManager.getLogger(FigureParameters.class);

    public static FigureParameters readParametersFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String typeLine = reader.readLine().trim().toUpperCase();

            FigureTypes figureType = FigureTypes.valueOf(typeLine);

            String parameterLine = reader.readLine();

            return new FigureParameters(figureType, parameterLine);
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid figure type.");
        }
    }
}
