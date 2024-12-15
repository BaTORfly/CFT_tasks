package ru.shift.models;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.logic.Storage;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static final Logger log = LogManager.getLogger(Producer.class);
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Getter
    private final int producerId;
    private final long producerTimeMillis;
    private final Storage storage;

    public Producer(long producerTimeMillis, Storage storage) {
        synchronized (Producer.class) {
            this.producerId = idCounter.getAndIncrement();
        }
        this.producerTimeMillis = producerTimeMillis;
        this.storage = storage;
    }

    @Override
    public void run() {
        log.info("Producer #{} started work", producerId);
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(producerTimeMillis);
                storage.produce(new Resource(), producerId);
            }
        } catch(InterruptedException e){
            log.error("Producer #{} was interrupted. Reason: {}", producerId, e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
