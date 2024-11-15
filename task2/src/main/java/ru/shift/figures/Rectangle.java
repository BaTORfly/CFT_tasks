package ru.shift.figures;

import java.util.Arrays;

public class Rectangle extends Figure {
    private final double length;
    private final double width;

    public Rectangle(FigureTypes name, String parameterLine) {
        double[] parameters;
        try{
            parameters = Arrays.stream(parameterLine.trim().split("\\s+"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        } catch(NumberFormatException ex){
            throw new IllegalArgumentException
                    ("Error parsing Rectangle parameters. Ensure all parameters are numbers.");
        }
        for (double parameter : parameters)
        {
            if (parameter <= 0)
                throw new IllegalArgumentException
                        ("Rectangle parameters must be positive. Found: " + parameter);
        }
        if (parameters.length != 2) {
            throw new IllegalArgumentException("Rectangle must have exactly 2 parameters, but found: "
                    + parameters.length);
        }
        this.length = Math.max(parameters[0], parameters[1]);
        this.width = Math.min(parameters[0], parameters[1]);
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String figureInfo() {
        return super.figureInfo()
                + "\nlength = " + FORMAT.format(length) + MEASUREMENT_UNIT
                + "\nwidth = " + FORMAT.format(width) + MEASUREMENT_UNIT
                + "\ndiagonal = " + FORMAT.format(calculateDiagonal()) + MEASUREMENT_UNIT
                + "\n}";
    }

    @Override
    public FigureTypes getType() {
        return FigureTypes.RECTANGLE;
    }

    @Override
    public double calculateArea() {
        return width * length;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * width * length;
    }

    public double calculateDiagonal(){
        return Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }
}
