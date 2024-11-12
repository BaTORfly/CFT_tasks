package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    private final double EPS = 1e-9;
    private static final String NAME = "RECTANGLE";
    private static final double[] PARAMETERS = new double[]{8.32, 4.56};
    private static Rectangle rectangle;
    private final double LENGTH = PARAMETERS[0];
    private final double WIDTH = PARAMETERS[1];

    @BeforeAll
    static void createRectangle() {
        rectangle = new Rectangle(NAME, PARAMETERS);
    }
    @Test
    void notTwoParametersThrowIllegalArgumentException() {
        double[] inavlidParameters = new double[]{3.0};
        String expectedExceptionMessage = "Rectangle must have exactly 2 parameters, but found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(NAME, inavlidParameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void testCalculateArea() {
        double expectedArea = WIDTH * LENGTH;

        double actualArea = rectangle.calculateArea();

        Assertions.assertEquals(expectedArea, actualArea, EPS);
    }

    @Test
    void testCalculatePerimeter() {
        double expectedPerimeter = 2 * WIDTH * LENGTH;

        double actualPerimeter = rectangle.calculatePerimeter();

        Assertions.assertEquals(expectedPerimeter, actualPerimeter, EPS);
    }

    @Test
    void testCalculateDiagonal(){
        double expectedDiagonal = Math.sqrt(Math.pow(LENGTH, 2) + Math.pow(WIDTH, 2));

        double actualDiagonal = rectangle.calculateDiagonal();

        Assertions.assertEquals(expectedDiagonal, actualDiagonal, EPS);
    }


}
