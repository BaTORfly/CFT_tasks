package ru.shift.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class GeometricSeries extends Task {
    private static final Logger log = LogManager.getLogger(GeometricSeries.class);
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    public GeometricSeries(long startRow, long endRow) {
        super(idCounter.getAndIncrement(), startRow, endRow);
    }

    @Override
    public Double executeAndReturnTaskResult() {
        double result = 0.0;
        log.info("Task #{} started execute. Start {}, end {}", id, startRow, endRow);
        for (long i = startRow; i <= endRow; i++) {
            result += (1.0) / Math.pow(2, i);
        }
        log.info("Task #{} finished with result: {} ", id, result);
        return result;
    }
}
