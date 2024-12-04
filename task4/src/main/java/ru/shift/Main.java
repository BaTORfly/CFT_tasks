package ru.shift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.tasks.GeometricSeries;
import ru.shift.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final int COUNT_THREADS = Runtime.getRuntime().availableProcessors();
    private static final Logger log = LogManager.getLogger(Main.class);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_THREADS);
        List<Task> tasks = new ArrayList<>();
        List<Future<?>> futures = new ArrayList<>();

        long N = getPositiveLong();

        log.info("Count of threads: {}", COUNT_THREADS);
        log.info("Number of rows N: {}", N);

        long chunkSize = (N+1) / COUNT_THREADS;

        for (int thr = 0; thr < COUNT_THREADS; thr++) {
            final long start = GeometricSeries.getBase() + thr * chunkSize;
            final long end = (thr == COUNT_THREADS - 1) ? N : start + chunkSize - 1;

            Task task = new GeometricSeries(thr, start, end);
            tasks.add(task);
            futures.add(executorService.submit(task));

            log.info("Created and submitted task #{}", thr);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                log.error("Error while waiting for task completion: ", e);
            }
        }

        executorService.shutdown();

        double sum = 0.0;
        for (Task task : tasks) {
            sum += task.getResult();
        }

        log.info("Result: {}", sum);
    }

    private static long getPositiveLong() {
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
