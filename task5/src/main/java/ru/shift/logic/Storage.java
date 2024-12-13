package ru.shift.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.models.Resource;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {
    private static final Logger log = LogManager.getLogger(Storage.class);
    private final long LIMIT_STORAGE_SIZE;
    private final Queue<Resource> storage;

    public Storage(long storageSize) {
        this.LIMIT_STORAGE_SIZE = storageSize;
        storage = new LinkedList<>();
    }

    public synchronized void putResource(Resource resource, int producerId) throws InterruptedException {
        while (storage.size() >= LIMIT_STORAGE_SIZE) {
            log.info("Storage is full. Resource id={} , producerId={} waiting",
                    resource.getId(), producerId);
            wait();
        }
        storage.add(resource);
        log.info("Producer id={} added to storage, storage size={}", producerId, storage.size());

        notifyAll();
    }

    public synchronized void takeResource(int consumerId) {
        while (storage.isEmpty()) {
            try{
                log.info("Storage is empty. Consumer id={} waiting", consumerId);
                wait();
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("An error occured while waiting for storage", e);
            }
        }

        Resource resource = storage.remove();
        log.info("Consumer id={} take recource id={}, storage size={}",
                consumerId, resource.getId(), storage.size());

        notifyAll();
    }
}
