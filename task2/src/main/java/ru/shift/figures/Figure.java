package ru.shift.figures;

import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class Figure {
    static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    final String MEASUREMENT_UNIT = " см.";

    public String figureInfo() {
        return "Figure{" +
                "\nname = " + getType().name() +
                "\narea = " + FORMAT.format(calculateArea()) + MEASUREMENT_UNIT +
                "\nperimeter = " + FORMAT.format(calculatePerimeter()) + MEASUREMENT_UNIT;
    }

    public static double[] parseDoubles (String line, int expectedCount) {
        double[] parameters;
        try {
            parameters = Arrays.stream(line.trim().split("\\s+"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing parameters. Ensure all parameters are numbers.");
        }

        if (parameters.length != expectedCount)
            throw new IllegalArgumentException("Expected " + expectedCount + " parameters, but found: "
                    + parameters.length);

        for (double parameter : parameters) {
            if (parameter <= 0) {
                throw new IllegalArgumentException("All parameters must be positive. Found: " + parameter);
            }
        }
        return parameters;
    }

    public abstract FigureTypes getType();

    public abstract double calculateArea();

    public abstract double calculatePerimeter();
}
