package ru.shift.models;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.logic.Storage;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private static final Logger log = LogManager.getLogger(Consumer.class);
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Getter
    private final int consumerId;
    private final long consumerTimeMillis;
    private final Storage storage;

    public Consumer(long consumerTimeMillis, Storage storage) {
        this.consumerId = idCounter.getAndIncrement();
        this.consumerTimeMillis = consumerTimeMillis;
        this.storage = storage;
    }

    @Override
    public void run() {
        log.info("Consumer #{} started work", consumerId);
        try{
            while(!Thread.currentThread().isInterrupted()){
                Thread.sleep(consumerTimeMillis);
                storage.consume(consumerId);
            }
        } catch(InterruptedException e){
            log.error("Consumer #{} was interrupted. Reason: {}", e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }

}
