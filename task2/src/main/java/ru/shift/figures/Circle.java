package ru.shift.figures;

import static java.lang.Math.PI;

public class Circle extends Figure {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public static Circle create(String parameterLine){
        var doubles = Figure.parseDoubles(parameterLine, 1);
        return new Circle(doubles[0]);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String figureInfo() {
        return super.figureInfo()
                + "\nradius = " + FORMAT.format(radius) + MEASUREMENT_UNIT
                + "\ndiameter = " + FORMAT.format(calculateDiameter()) + MEASUREMENT_UNIT
                + "\n}";
    }

    @Override
    public FigureTypes getType() {
        return FigureTypes.CIRCLE;
    }

    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }

    public double calculateDiameter(){
        return radius * 2;
    }
}
