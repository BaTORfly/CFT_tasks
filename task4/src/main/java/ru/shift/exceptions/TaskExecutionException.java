package ru.shift.exceptions;

public class TaskExecutionException extends Exception {
    public TaskExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
