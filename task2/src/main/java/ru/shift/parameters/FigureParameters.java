package ru.shift.parameters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public record FigureParameters(String typeLine, String parameterLine) {

    public static FigureParameters readParametersFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String typeLine = reader.readLine();
            if (typeLine == null) {
                throw new IllegalArgumentException("File does not contain type line.");
            }
            typeLine = typeLine.trim();

            String parameterLine = reader.readLine();
            if (parameterLine == null) {
                throw new IllegalArgumentException("File does not contain parameter line.");
            }
            return new FigureParameters(typeLine, parameterLine);
        } catch (IOException e) {
            throw new IOException("Error reading the file at: " + filePath, e);
        }
    }
}
