package ru.shift.tasks;

public class TaskFactory {

    private TaskFactory() {}

    public static Task createTask(Series typeSeries, long startRow, long endRow) {
        Task task = null;

        switch (typeSeries){
            case GEOMETRIC_SERIES -> task = new GeometricSeries(startRow, endRow);
            default -> throw new IllegalArgumentException("Invalid series type");
        }

        return task;
    }
}
