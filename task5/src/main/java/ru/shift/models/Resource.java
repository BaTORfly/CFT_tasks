package ru.shift.models;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Resource {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int id;

    public Resource() {
        synchronized (Resource.class){
            this.id = idCounter.getAndIncrement();
        }
    }
}
