package ru.shift.figures;

import java.text.DecimalFormat;

public abstract class Figure {
    static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    final String MEASUREMENT_UNIT = " см.";

    public String figureInfo() {
        return "Figure{" +
                "\nname = " + getType().name() +
                "\narea = " + FORMAT.format(calculateArea()) + MEASUREMENT_UNIT +
                "\nperimeter = " + FORMAT.format(calculatePerimeter()) + MEASUREMENT_UNIT;
    }

    public abstract FigureTypes getType();

    public abstract double calculateArea();

    public abstract double calculatePerimeter();
}
