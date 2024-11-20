package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.shift.Exceptions.TriangleException;

public class TriangleTest {
    private final double EPS = 1e-9;
    private static Triangle triangle;
    private final double AB = 4.6;
    private final double BC = 7.4;
    private final double AC = 5.3;

    @BeforeAll
    static void createTriangle() throws TriangleException {
        triangle = new Triangle(4.6, 7.4, 5.3);
    }

    @Test
    void invalidTriangleSidesThrowTriangleException() {
        String expectedExceptionMessage =
                "Invalid triangle sides: the sum of any sides must be greater than the third side. Your sides: ";

        TriangleException exception = Assertions.assertThrows(
                TriangleException.class,
                () -> new Triangle(4.6, 34.4, 5.3)
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
