package ru.shift;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.config.Config;

import java.io.IOException;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = null;
        try{
            config = new Config("config.properties");
        } catch (IOException ex){
            log.error("Failed to load app configuration " + ex.getMessage());
        }
    }
}