package ru.shift.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TriangleException extends Exception {
    private List<Double> sides;

    public TriangleException(String message, double side1, double side2, double side3) {
        super(message);

        sides = new ArrayList<>();
        sides.add(side1);
        sides.add(side2);
        sides.add(side3);
    }

    public String getSides() {
        return sides.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
