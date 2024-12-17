package ru.shift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.config.Config;
import ru.shift.logic.Storage;
import ru.shift.models.Consumer;
import ru.shift.models.Producer;

import java.io.IOException;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = null;
        try{
            config = new Config("config.properties");
        } catch (IOException ex){
            log.error("Failed to load app configuration " + ex.getMessage());
            System.exit(1);
        }

        Storage storage = new Storage(config.getStorageSize());

        for (int i = 0; i < config.getProducerCount(); i++) {
            Producer producer = new Producer(config.getProducerTime(), storage);
            new Thread(producer).start();
        }

        for (int i = 0; i < config.getConsumerCount(); i++) {
            Consumer consumer = new Consumer(config.getConsumerTime(), storage);
            new Thread(consumer).start();
        }
    }
}