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
    private static final Logger log = LogManager.getLogger(FigureFactory.class);
    private FigureFactory() {}

    public static Figure createFigure(FigureParameters figureParameters) {
        Figure figure = null;
        try{
            switch(figureParameters.type()) {
                case "CIRCLE" -> figure = new Circle(figureParameters.type(), figureParameters.parameters());
                case "RECTANGLE" ->
                        figure = new Rectangle
                                (figureParameters.type(), figureParameters.parameters());
                case "TRIANGLE" ->
                        figure = new Triangle
                                (figureParameters.type(), figureParameters.parameters());
            }
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
        } catch (TriangleException ex) {
            log.error("{}{}", ex.getMessage(), ex.getSides());
        }
        return figure;
    }
}
