package ru.shift.models;

import lombok.AllArgsConstructor;
import ru.shift.logic.Storage;

@AllArgsConstructor
public class Producer extends Thread {
    private final int id;
    private final long producerTimeMillis;
    private final Storage storage;

    @Override
    public void run() {
        while (!isInterrupted()) {
            try{
                sleep(producerTimeMillis);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
