package ru.shift.Factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.Exceptions.TriangleException;
import ru.shift.figures.Circle;
import ru.shift.figures.Figure;
import ru.shift.figures.Rectangle;
import ru.shift.figures.Triangle;
import ru.shift.parameters.FigureParameters;

public class FigureFactory {

    private FigureFactory() {}

    public static Figure createFigure(FigureParameters figureParameters) {
        Figure figure = null;

        switch(figureParameters.type()) {
            case CIRCLE -> figure = new Circle(figureParameters.type(), figureParameters.parameterLine());
            case RECTANGLE ->
                    figure = new Rectangle
                            (figureParameters.type(), figureParameters.parameterLine());
            case TRIANGLE ->
                    figure = new Triangle
                            (figureParameters.type(), figureParameters.parameterLine());
        }

        return figure;
    }
}
