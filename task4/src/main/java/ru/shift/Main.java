package ru.shift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.exceptions.TaskExecutionException;
import ru.shift.tasks.Series;
import ru.shift.logic.SeriesExecutor;

import java.util.Scanner;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        long N = getPositiveLong();
        log.info("Number of rows N: {}", N);

        Series typeSeries = Series.GEOMETRIC_SERIES;
        SeriesExecutor seriesExecutor = new SeriesExecutor(N, typeSeries);
        double sum = 0.0;

        try{
            sum = seriesExecutor.executeSumSeries();
        } catch (TaskExecutionException e){
            log.error("Error while executing series", e);
        }

        log.info("Sum of series: {}", sum);
    }

    public static long getPositiveLong() {
        Scanner scanner = new Scanner(System.in);
        long N;
        while (true) {
            System.out.println("Enter the number of row members (N, must be a positive integer): ");
            if (scanner.hasNextLong()) {
                N = scanner.nextLong();
                if (N > 0) {
                    log.info("User entered a valid value for N: {}", N);
                    return N;
                } else {
                    log.warn("User entered a negative value for N: {}", N);
                    System.out.println("N must be greater than 0. Please try again.");
                }
            } else {
                log.error("Invalid input detected. User did not enter a valid integer.");
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }
}
