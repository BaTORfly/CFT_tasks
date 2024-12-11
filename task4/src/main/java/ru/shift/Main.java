package ru.shift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.tasks.Series;
import ru.shift.logic.SeriesExecutor;
import ru.shift.util.Utils;

import java.util.Scanner;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        long N = Utils.getPositiveLong();
        log.info("Number of rows N: {}", N);

        Series typeSeries = Series.GEOMETRIC_SERIES;
        SeriesExecutor seriesExecutor = new SeriesExecutor(N, typeSeries);
        double sum = 0.0;

        try{
            sum = seriesExecutor.executeSumSeries();
        } catch (Exception e){
            log.error(e);
        }

        log.info("Sum of series: {}", sum);
    }
}
