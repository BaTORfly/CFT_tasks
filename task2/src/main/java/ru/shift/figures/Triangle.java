package ru.shift.figures;

import ru.shift.Exceptions.TriangleException;

public class Triangle extends Figure {

    private final double AB;
    private final double BC;
    private final double AC;

    public Triangle(double AB, double BC, double AC) throws TriangleException {
        this.AB = AB;
        this.BC = BC;
        this.AC = AC;

        if (!isTriangleValid(AB, BC, AC))
            throw new IllegalArgumentException(
                    "Invalid triangle sides: the sum of any sides must be greater than the third side. Your sides: "
            + AB + ", " + BC + ", " + AC);
    }

    public static Triangle create(String parameterLine) throws TriangleException {
        var doubles = Figure.parseDoubles(parameterLine, 3);
        return new Triangle(doubles[0], doubles[1], doubles[2]);
    }

    public double getAB() {
        return AB;
    }

    public double getBC() {
        return BC;
    }

    public double getAC() {
        return AC;
    }

    private boolean isTriangleValid(double AB, double BC, double AC) {
        return (AB + BC > AC) && (AB + AC > BC) && (BC + AC > AB);
    }
    @Override
    public String figureInfo() {
        return super.figureInfo()
                + "\nAB = " + FORMAT.format(AB) + MEASUREMENT_UNIT + ", angleC = " + FORMAT.format(calculateAngleC())
                + "\nBC = " + FORMAT.format(BC) + MEASUREMENT_UNIT + ", angleA = " + FORMAT.format(calculateAngleA())
                + "\nAC = " + FORMAT.format(AC) + MEASUREMENT_UNIT + ", angleB = " + FORMAT.format(calculateAngleB())
                + "\n}";
    }

    @Override
    public FigureTypes getType() {
        return FigureTypes.TRIANGLE;
    }

    @Override
    public double calculateArea() {
        double semiPerimeter = (AB + BC + AC)/2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - BC) * (semiPerimeter - AC));
    }

    @Override
    public double calculatePerimeter() {
        return AC + AB + BC;
    }

    public double calculateAngleA(){
        return Math.acos((Math.pow(AC, 2) + Math.pow(AB, 2) - Math.pow(BC, 2)) / (2 * AC * AB));
    }

    public double calculateAngleB(){
        return Math.acos((Math.pow(BC, 2) + Math.pow(AB, 2) - Math.pow(AC, 2)) / (2 * BC * AB));
    }

    public double calculateAngleC(){
        return Math.acos((Math.pow(BC, 2) + Math.pow(AC, 2) - Math.pow(AB, 2)) / (2 * BC * AC));
    }
}
