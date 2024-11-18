package ru.shift.figures;

public class Rectangle extends Figure {
    private final double length;
    private final double width;

    public Rectangle(double side1, double side2) {
        this.length = Math.max(side1, side2);
        this.width = Math.min(side1, side2);
    }

    public static Rectangle create (String parameterLine){
        var doubles = Figure.parseDoubles(parameterLine, 2);
        return new Rectangle(doubles[0], doubles[1]);
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
