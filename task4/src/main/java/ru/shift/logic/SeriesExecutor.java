package ru.shift.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.tasks.Series;
import ru.shift.tasks.Task;
import ru.shift.tasks.TaskFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SeriesExecutor {

    private static Logger log = LogManager.getLogger(SeriesExecutor.class);
    private static final int COUNT_THREADS = Runtime.getRuntime().availableProcessors();

    private final int MIN_N_FOR_MULTITHREADING = 100;
    private final long N;
    private final Series typeSeries;
    private TaskFactory taskFactory;

    public SeriesExecutor(long N, Series typeSeries) {
        this.N = N;
        this.typeSeries = typeSeries;
    }

    public Double executeSumSeries() throws Exception {
        return (N <= MIN_N_FOR_MULTITHREADING) ? calculateInSingleThread() : calculateInMultipleThreads();
    }

    private double calculateInSingleThread() throws Exception {
        log.info("N is less than {}. Calculations will be done in a single thread.",
                MIN_N_FOR_MULTITHREADING);

        double sum = 0.0;

        long start = typeSeries.getBase();
        long end = N;
        Task task = taskFactory.createTask(typeSeries, start, end);
        try {
            sum = task.call();
        } catch (Exception e) {
            throw new Exception("Error while calculating in single thread: ", e);
        }

        return sum;
    }

    private double calculateInMultipleThreads() throws Exception {
        log.info("N is greater than or equal to {}. Calculations will be done in multiple threads." +
                "(threads count = {})",
                MIN_N_FOR_MULTITHREADING, COUNT_THREADS);

        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_THREADS);
        List<Task> tasks = new ArrayList<>();
        List<Future<Double>> futures = new ArrayList<>();

        long chunkSize = (N+1) / COUNT_THREADS;

        for (int thr = 0; thr < COUNT_THREADS; thr++) {
            final long start = typeSeries.getBase() + thr * chunkSize;
            final long end = Math.min(N, start + chunkSize -1);

            Task task = taskFactory.createTask(typeSeries, start, end);
            tasks.add(task);
            futures.add(executorService.submit(task));

            log.info("Created and submitted task #{}", thr);
        }

        double sum = 0.0;

        for (Future<Double> future : futures) {
            try {
                sum += future.get();
            } catch (Exception e) {
                throw new Exception("Error while waiting for task completion: ", e);
            }
        }

        executorService.shutdown();

        return sum;
    }
}
