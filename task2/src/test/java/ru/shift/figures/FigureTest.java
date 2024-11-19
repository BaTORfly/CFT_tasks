package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shift.figures.Figure;

public class FigureTest {
    @Test
    void testCorrectParseDoubles() {
        String parameterLine = "4.32 5.5";
        int expectedCount = 2;

        double[] expectedResult = new double[]{4.32, 5.5};

        double[] actualResult = Figure.parseDoubles(parameterLine, expectedCount);

        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void notDoublesLineThrowsIllegalArgumentException(){
        String parameterLine = "4.32 5.5 String";
        int expectedCount = 2;
        String expectedExceptionMessage = "Error parsing parameters. Ensure all parameters are numbers.";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Figure.parseDoubles(parameterLine, expectedCount));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void parameterLengthIsNotEqualExpectedCountThrowIllegalArgumentException(){
        String parameterLine = "4.3 6.7 3";
        int expectedCount = 2;
        String expectedExceptionMessage = "Expected " + expectedCount + " parameters, but found: "
                + 3;

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Figure.parseDoubles(parameterLine, expectedCount));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void negativeParameterThrowsIllegalArgumentException(){
        String parameterLine = "-4.32 5.5";
        int expectedCount = 2;
        String expectedExceptionMessage = "All parameters must be positive. Found: " + "-4.32";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Figure.parseDoubles(parameterLine, expectedCount));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }
}
