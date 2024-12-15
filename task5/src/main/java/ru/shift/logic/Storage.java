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

    private final Object LOCK_PRODUCE = new Object();
    private final Object LOCK_CONSUME = new Object();

    public Storage(long storageSize) {
        this.LIMIT_STORAGE_SIZE = storageSize;
        storage = new LinkedList<>();
    }

    public void produce(Resource resource, int producerId) throws InterruptedException {
        try{
            boolean added = false;
            boolean wasEmpty;
            int currentSize;
            synchronized (LOCK_PRODUCE) {
                while (true){
                    synchronized (storage){
                        wasEmpty = storage.isEmpty();
                        if (storage.size() < LIMIT_STORAGE_SIZE){
                            storage.offer(resource);
                            added = true;
                        }
                        currentSize = storage.size();
                    }

                    if (added){
                        log.info("Resource #{} was added to the storage by Producer #{}",
                                resource.getId(), producerId);
                        break;
                    }

                    log.info("Producer #{} wait space in storage", producerId);
                    LOCK_PRODUCE.wait();
                    log.info("Producer #{} return to producing resource", producerId);
                }

                if (currentSize < LIMIT_STORAGE_SIZE){
                    LOCK_PRODUCE.notify();
                }
            }

            if (wasEmpty){
                synchronized (LOCK_CONSUME){
                    LOCK_CONSUME.notify();
                }
            }

        } catch (InterruptedException e) {
            throw new InterruptedException("Exception occurred while waiting producing resource.");
        }
    }

    public void consume(int consumerId) throws InterruptedException {
        try{
            Resource resource;
            int currentSize;
            boolean wasFull;
            synchronized (LOCK_CONSUME){
                while (true){
                    synchronized (storage){
                        wasFull = storage.size() == LIMIT_STORAGE_SIZE;
                        resource = storage.poll();
                        currentSize = storage.size();
                    }

                    if (resource != null){
                        log.info("Consumer #{} was taken resource from the storage. Storage size = {}",
                                consumerId, currentSize);
                        break;
                    }

                    log.info("Consumer #{} waiting for resources to appear in the storage", consumerId);
                    LOCK_CONSUME.wait();
                    log.info("Consumer #{} return to consuming resource", consumerId);
                }

                if (currentSize > 0){
                    LOCK_CONSUME.notify();
                }
            }
            if (wasFull){
                LOCK_PRODUCE.notify();
            }
        } catch (InterruptedException e) {
            throw new InterruptedException("Exception occurred while waiting consuming resource");
        }
    }
}
