package ru.shift.figures;

import java.text.DecimalFormat;

public abstract class Figure {
    static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    final String MEASUREMENT_UNIT = " см.";

    String name;
    double area;
    double perimeter;

    public String figureInfo() {
        return "Figure{" +
                "\nname = " + name +
                "\narea = " + FORMAT.format(area) + MEASUREMENT_UNIT +
                "\nperimeter = " + FORMAT.format(perimeter) + MEASUREMENT_UNIT;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();
}
