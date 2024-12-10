package ru.shift.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Config {
    private int producerCount;
    private int consumerCount;
    private long producerTime;
    private long consumerTime;
    private int storageSize;

    public Config(String configFile) throws IOException {
        Properties prop = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if(inputStream == null)
                throw new IOException("Configuration file " + configFile + "not found in recources");
            prop.load(inputStream);
        }
        this.producerCount = Integer.parseInt(prop.getProperty("producerCount", "1"));
        this.consumerCount = Integer.parseInt(prop.getProperty("consumerCount", "1"));
        this.producerTime = Long.parseLong(prop.getProperty("producerTime", "1000"));
        this.consumerTime = Long.parseLong(prop.getProperty("consumerTime", "1000"));
        this.storageSize = Integer.parseInt(prop.getProperty("storageSize", "10"));
    }
}
