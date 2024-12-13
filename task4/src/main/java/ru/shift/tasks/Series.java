package ru.shift.tasks;

public enum Series {
    GEOMETRIC_SERIES(0);

    private final int base;

    Series(int base) {
        this.base = base;
    }

    public int getBase() {
        return base;
    }
}
