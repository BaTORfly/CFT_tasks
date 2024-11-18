package ru.shift.Factory;

import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import ru.shift.Exceptions.TriangleException;
import ru.shift.figures.*;
import ru.shift.parameters.FigureParameters;

public class FigureFactory {

    private FigureFactory() {}

    public static Figure createFigure(FigureParameters figureParameters) {
        Figure figure = null;
        FigureTypes type;
        try {
            type = FigureTypes.valueOf(figureParameters.typeLine().toUpperCase());
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid figure type: " + figureParameters.typeLine());
        }
        switch(type) {
            case CIRCLE ->
                figure = Circle.create(figureParameters.parameterLine());

            case RECTANGLE ->
                figure = Rectangle.create(figureParameters.parameterLine());

            case TRIANGLE ->
                figure = Triangle.create(figureParameters.parameterLine());
        }
        return figure;
    }
}
