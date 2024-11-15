package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    private final double EPS = 1e-9;
    private static final FigureTypes NAME = FigureTypes.RECTANGLE;
    private static final String PARAMETERS = "8.32 4.56";
    private static Rectangle rectangle;
    private final double LENGTH = rectangle.getLength();
    private final double WIDTH = rectangle.getWidth();

    @BeforeAll
    static void createRectangle() {
        rectangle = new Rectangle(NAME, PARAMETERS);
    }
    @Test
    void notTwoParametersThrowIllegalArgumentException() {
        String inavlidParameters = "3.0";
        String expectedExceptionMessage = "Rectangle must have exactly 2 parameters, but found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(NAME, inavlidParameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void notNumberParameterThrowsIllegalArgumentException() {
        String invalidParameter = "3.0 t 395";
        String expectedExceptionMessage =
                "Error parsing Rectangle parameters. Ensure all parameters are numbers.";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(NAME, invalidParameter));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void negativeParameterThrowsIllegalArgumentException() {
        String negativeParameter = "-1";

        String expectedExceptionMessage =
                "Rectangle parameters must be positive. Found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Rectangle(NAME, negativeParameter));

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
