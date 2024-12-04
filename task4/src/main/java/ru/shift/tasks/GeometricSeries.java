package ru.shift.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeometricSeries extends Task {
    private static final Logger log = LogManager.getLogger(GeometricSeries.class);
    private static final int BASE = 0;

    public GeometricSeries(int id, long startRow, long endRow) {
        super(id, startRow, endRow);
    }

    @Override
    public void executeTask() {
        log.info("Task #{} started execute", id);
        for (long i = startRow; i <= endRow; i++) {
            result += (1.0) / Math.pow(2, i);
        }
        log.info("Task #{} finished with result: {} ", id, result);
    }

    public static int getBase() {
        return BASE;
    }
}
