package ru.shift.figures;

import static java.lang.Math.PI;

public class Circle extends Figure {
    private final double radius;
    private double diameter;

    public Circle(String name, double parameters[]) {
        if (parameters.length != 1)
            throw new IllegalArgumentException("Circle must have exactly 1 parameter, but found: " + parameters.length);
        this.name = name;
        this.radius = parameters[0];
        this.area = calculateArea();
        this.perimeter = calculatePerimeter();
        this.diameter = calculateDiameter();
    }

    public double getRadius() {
        return radius;
    }


    @Override
    public String figureInfo() {
        return super.figureInfo()
                + "\nradius = " + FORMAT.format(radius) + MEASUREMENT_UNIT
                + "\ndiameter = " + FORMAT.format(diameter) + MEASUREMENT_UNIT
                + "\n}";
    }

    @Override
    public double calculateArea() {
        area = PI * radius * radius;
        return area;
    }

    @Override
    public double calculatePerimeter() {
        perimeter = 2 * PI * radius;
        return perimeter;
    }

    public double calculateDiameter(){
        diameter = radius * 2;
        return diameter;
    }
}
