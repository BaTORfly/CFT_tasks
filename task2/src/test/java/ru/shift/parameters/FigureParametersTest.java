package ru.shift.parameters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FigureParametersTest {
    @Test
    void testTypeConvertedToUpperCase(){
        String type = "circle";

        String expected = "CIRCLE";

        FigureParameters fp = new FigureParameters(type, new double[]{4.0});
        Assertions.assertEquals(expected, fp.type());
    }

    @Test
    void invalidFigureTypeShouldThrowIllegalArgumentException(){
        String type = "unknownType";
        double[] parameters = new double[]{4.0};

        String expectedExceptionMessage = "Invalid figure type";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new FigureParameters(type, parameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void nullParameterShouldThrowIllegalArgumentException(){
        String type = "RECTANGLE";
        double[] parameters = null;

        String expectedExceptionMessage = "Set of figure parameters cannot be null or empty";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new FigureParameters(type, parameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    void emptyParametersShouldThrowIllegalArgumentException(){
        String type = "RECTANGLE";
        double[] parameters = new double[]{};

        String expectedExceptionMessage = "Set of figure parameters cannot be null or empty";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new FigureParameters(type, parameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void negativeParametersShouldThrowIllegalArgumentException(){
        String type = "triangle";
        double[] parameters = new double[]{4.0, -2.7};

        String expectedExceptionMessage = "Figure parameters must be positive. Found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new FigureParameters(type, parameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void zeroParametersShouldThrowIllegalArgumentException(){
        String type = "triangle";
        double[] parameters = new double[]{3.0, 0.0};

        String expectedExceptionMessage = "Figure parameters must be positive. Found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new FigureParameters(type, parameters));

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

}
