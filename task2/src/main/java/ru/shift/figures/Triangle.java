package ru.shift.figures;

import ru.shift.Exceptions.TriangleException;

public class Triangle extends Figure {

    private final double AB;
    private final double BC;
    private final double AC;

    private double angleA;
    private double angleB;
    private double angleC;

    public Triangle(String name, double[] parameters) throws TriangleException {
        if (parameters.length != 3)
            throw new IllegalArgumentException("Triangle must have exactly 3 parameters, but found: "
                    + parameters.length);
        this.name = name;
        this.AB = parameters[0];
        this.BC = parameters[1];
        this.AC = parameters[2];
        if (!isTriangleValid(AB, BC, AC))
            throw new TriangleException(
                    "Invalid triangle sides: the sum of any sides must be greater than the third side. Your sides: ",
                    AB, BC, AC);

        this.angleA = calculateAngleA();
        this.angleB = calculateAngleB();
        this.angleC = calculateAngleC();
        this.perimeter = calculatePerimeter();
        this.area = calculateArea();
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
                + "\nAB = " + FORMAT.format(AB) + MEASUREMENT_UNIT + ", angleC = " + FORMAT.format(angleC)
                + "\nBC = " + FORMAT.format(BC) + MEASUREMENT_UNIT + ", angleA = " + FORMAT.format(angleA)
                + "\nAC = " + FORMAT.format(AC) + MEASUREMENT_UNIT + ", angleB = " + FORMAT.format(angleB)
                + "\n}";
    }

    @Override
    public double calculateArea() {
        double semiPerimeter = (AB + BC + AC)/2;
        area = Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - BC) * (semiPerimeter - AC));
        return area;
    }

    @Override
    public double calculatePerimeter() {
        perimeter = AC + AB + BC;
        return perimeter;
    }

    public double calculateAngleA(){
        angleA = Math.acos((Math.pow(AC, 2) + Math.pow(AB, 2) - Math.pow(BC, 2)) / (2 * AC * AB));
        return angleA;
    }

    public double calculateAngleB(){
        angleB = Math.acos((Math.pow(BC, 2) + Math.pow(AB, 2) - Math.pow(AC, 2)) / (2 * BC * AB));
        return angleB;
    }

    public double calculateAngleC(){
        angleC = Math.acos((Math.pow(BC, 2) + Math.pow(AC, 2) - Math.pow(AB, 2)) / (2 * BC * AC));
        return angleC;
    }
}
