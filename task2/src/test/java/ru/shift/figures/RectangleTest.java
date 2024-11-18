package ru.shift.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    private final double EPS = 1e-9;
    private static final FigureTypes NAME = FigureTypes.RECTANGLE;
    private static Rectangle rectangle;
    private final double LENGTH = 8.32;
    private final double WIDTH = 4.56;

    @BeforeAll
    static void createRectangle() {
        rectangle = new Rectangle(4.56, 8.32);
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
