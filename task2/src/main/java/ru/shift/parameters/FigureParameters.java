package ru.shift.parameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.figures.FigureTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public record FigureParameters(String type, double[] parameters) {

    private static final Logger log = LogManager.getLogger(FigureParameters.class);

    public FigureParameters {
        type = type.toUpperCase();
        try{
            FigureTypes.valueOf(type);
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid figure type" + type);
        }

        if (parameters == null || parameters.length == 0) {
            throw new IllegalArgumentException("Set of figure parameters cannot be null or empty");
        }

        for (double line : parameters) {
            if (line <= 0) {
                throw new IllegalArgumentException("Figure parameters must be positive. Found: " + line);
            }
        }
    }

    public static FigureParameters readParametersFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String figureType = reader.readLine().trim();
            String line = reader.readLine();
            double[] params = Arrays.stream(line.trim().split("\\s+"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            return new FigureParameters(figureType, params);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error parsing figure parameters. Ensure all parameters are valid numbers.");
        }
    }
}
