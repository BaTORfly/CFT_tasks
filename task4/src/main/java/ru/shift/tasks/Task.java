package ru.shift.tasks;

import java.util.concurrent.Callable;

public abstract class Task implements Callable<Double> {
    protected int id;
    protected long startRow;
    protected long endRow;

    protected Task(int id, long startRow, long endRow) {
        this.id = id;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    protected abstract Double executeAndReturnTaskResult();

    @Override
    public Double call(){
        return executeAndReturnTaskResult();
    }
}
