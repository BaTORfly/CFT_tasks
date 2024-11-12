package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.shift.Exceptions.TriangleException;

public class TriangleTest {
    private final double EPS = 1e-9;
    private static final String NAME = "TRIANGLE";
    private static final double[] PARAMETERS = new double[]{4.6, 7.4, 5.3};
    private static Triangle triangle;
    private final double AB = PARAMETERS[0];
    private final double BC = PARAMETERS[1];
    private final double AC = PARAMETERS[2];

    @BeforeAll
    static void createTriangle() throws TriangleException {
        triangle = new Triangle(NAME, PARAMETERS);
    }

    @Test
    void notThreeParametersThrowIllegalArgumentException() {
        double[] invalidParameters = new double[]{3.5, 3, 6.8, 4};
        String expectedExceptionMessage = "Triangle must have exactly 3 parameters, but found: ";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(NAME, invalidParameters)
        );

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void invalidTriangleSidesThrowTriangleException() {
        double[] invalidParameters = new double[]{4.6, 34.4, 5.3};
        String expectedExceptionMessage =
                "Invalid triangle sides: the sum of any sides must be greater than the third side. Your sides: ";

        TriangleException exception = Assertions.assertThrows(
                TriangleException.class,
                () -> new Triangle(NAME, invalidParameters)
        );

        Assertions.assertTrue(exception.getMessage().contains(expectedExceptionMessage));
    }

    @Test
    void testCalculateArea(){
        double semiPerimeter = (AB + BC + AC)/2;

        double expectedArea = Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - BC) * (semiPerimeter - AC));

        double actualArea = triangle.calculateArea();

        Assertions.assertEquals(expectedArea, actualArea, EPS);
    }

    @Test
    void testCalculatePerimeter(){
        double expectedPerimeter = AB + BC + AC;

        double actualPerimeter = triangle.calculatePerimeter();

        Assertions.assertEquals(expectedPerimeter, actualPerimeter, EPS);
    }

    @Test
    void testCalculateAngleA(){
        double expectedAngleA = Math.acos((Math.pow(AC, 2) + Math.pow(AB, 2) - Math.pow(BC, 2)) / (2 * AC * AB));

        double actualAngleA = triangle.calculateAngleA();

        Assertions.assertEquals(expectedAngleA, actualAngleA, EPS);
    }

    @Test
    void testCalculateAngleB(){
        double expectedAngleB = Math.acos((Math.pow(BC, 2) + Math.pow(AB, 2) - Math.pow(AC, 2)) / (2 * BC * AB));

        double actualAngleB = triangle.calculateAngleB();

        Assertions.assertEquals(expectedAngleB, actualAngleB, EPS);
    }

    @Test
    void testCalculateAngleC(){
        double expectedAngleC = Math.acos((Math.pow(BC, 2) + Math.pow(AC, 2) - Math.pow(AB, 2)) / (2 * BC * AC));

        double actualAngleC = triangle.calculateAngleC();

        Assertions.assertEquals(expectedAngleC, actualAngleC, EPS);
    }
}
