package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CircleTest {
    private final double EPS = 1e-9;
    private static final String NAME = "CIRCLE";
    private static final double[] PARAMETERS = new double[]{6.34};
    private static Circle circle;
    private final double RADIUS = PARAMETERS[0];

    @BeforeAll
    static void createCircle(){
        circle = new Circle(NAME, PARAMETERS);
    }

    @Test
    void notOneParameterThrowsIllegalArgumentException() {
        double[] invalidParameters = new double[]{3.0, 3.4};
        String expectedExceptionMessage = "Circle must have exactly 1 parameter, but found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Circle(NAME, invalidParameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
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
