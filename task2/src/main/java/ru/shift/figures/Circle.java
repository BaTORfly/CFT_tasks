package ru.shift.figures;

import java.security.InvalidParameterException;
import java.util.Arrays;

import static java.lang.Math.PI;

public class Circle extends Figure {
    private final double radius;

    public Circle(FigureTypes name, String parameterLine) {
        try{
            this.radius = Math.abs(Double.parseDouble(parameterLine.trim()));
        }catch(NumberFormatException ex){
            throw new IllegalArgumentException
                    ("Error when parsing Circle parameters. Make sure you enter one parameter and it is a number.");
        }
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
