package ru.shift.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TriangleException extends IllegalArgumentException {

    public TriangleException(String message) {
        super(message);
    }
}
