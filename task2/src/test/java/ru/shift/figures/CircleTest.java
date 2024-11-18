package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CircleTest {
    private final double EPS = 1e-9;
    private static final FigureTypes NAME = FigureTypes.CIRCLE;
    private static final double PARAMETERS = 6.34;
    private static Circle circle;
    private final double RADIUS = circle.getRadius();

    @BeforeAll
    static void createCircle(){
        circle = new Circle(PARAMETERS);
    }

    @Test
    void testCalculateArea(){
        double expectedArea = Math.PI * RADIUS * RADIUS;

        double actualArea = circle.calculateArea();

        Assertions.assertEquals(expectedArea, actualArea, EPS);
    }

    @Test
    void testCalculatePerimeter(){
        double expectedPerimeter = 2 * Math.PI * RADIUS;

        double actualPerimeter = circle.calculatePerimeter();

        Assertions.assertEquals(expectedPerimeter, actualPerimeter, EPS);
    }

    @Test
    void testCalculateDiameter(){
        double expectedDiameter = RADIUS * 2;

        double actualDiameter = circle.calculateDiameter();

        Assertions.assertEquals(expectedDiameter, actualDiameter, EPS);
    }
}
