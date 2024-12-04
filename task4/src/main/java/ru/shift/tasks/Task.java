package ru.shift.tasks;

public abstract class Task implements Runnable {
    protected int id;
    protected long startRow;
    protected long endRow;
    protected double result = 0.0;

    public Task(int id, long startRow, long endRow) {
        this.id = id;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    public abstract void executeTask();

    @Override
    public void run() {
        executeTask();
    }

    public double getResult() {
        return result;
    }
}
