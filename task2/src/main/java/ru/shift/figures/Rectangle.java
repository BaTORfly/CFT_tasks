package ru.shift.figures;

public class Rectangle extends Figure {
    private final double length;
    private final double width;
    private double diagonal;

    public Rectangle(String name, double[] parameters) {
        if (parameters.length != 2)
            throw new IllegalArgumentException("Rectangle must have exactly 2 parameters, but found: "
                    + parameters.length);
        this.name = name;
        this.length = Math.max(parameters[0], parameters[1]);
        this.width = Math.min(parameters[0], parameters[1]);
        this.area = calculateArea();
        this.perimeter = calculatePerimeter();
        this.diagonal = calculateDiagonal();
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
                + "\ndiagonal = " + FORMAT.format(diagonal) + MEASUREMENT_UNIT
                + "\n}";
    }

    @Override
    public double calculateArea() {
        area = width * length;
        return area;
    }

    @Override
    public double calculatePerimeter() {
        perimeter = 2 * width * length;
        return perimeter;
    }

    public double calculateDiagonal(){
        diagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
        return diagonal;
    }
}
