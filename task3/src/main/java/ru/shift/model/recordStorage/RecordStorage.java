package ru.shift.model.recordStorage;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shift.model.gameField.listeners.HighRecordModelListener;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordStorage implements RecordModel {
    private static final Logger log = LogManager.getLogger(RecordStorage.class);
    private static final int MAX_COUNT_OF_RECORDS = 3;
    private static final int MAX_PARTS_OF_NAME = 3;
    private static final int NAME_INDEX = 0;
    private static final int TIME_INDEX = 1;
    private static final int MAX_LENGTH_NAME = 15;
    private static final String RECORDS_FILE = "records.txt";

    /**
     * Records Table. Key - level, value - String array: name and time
     */
    private final Map<Integer, String[]> recordTimeMap;

    private final HighRecordModelListener highRecordWindow;

    @Setter
    private int timeRecord;

    @Setter
    private int levelRecord;


    public RecordStorage(HighRecordModelListener highRecordWindow) {
        this.highRecordWindow = highRecordWindow;
        recordTimeMap = new HashMap<>(MAX_COUNT_OF_RECORDS);
        initializeCurrentRecords();
    }

    @Override
    public void setNewRecord(String recordName) {
        recordName = validateName(recordName.trim());
        List<String> records = new ArrayList<>(MAX_COUNT_OF_RECORDS);

        for (int i = 0; i < MAX_COUNT_OF_RECORDS; i++) {
            String[] record = recordTimeMap.get(i);
            if (record != null) {
                records.add(record[NAME_INDEX] + "-" + record[TIME_INDEX]);
            } else {
                records.add("");
            }
        }

        records.set(levelRecord, recordName + "-" + timeRecord);
        recordTimeMap.put(levelRecord, new String[]{recordName,Integer.toString(timeRecord)});

        highRecordWindow.updateHighRecord(recordName, timeRecord, levelRecord);

        try (BufferedWriter br = new BufferedWriter(
                new FileWriter(Paths.get("").toAbsolutePath() + File.separator + RECORDS_FILE))) {
            for (String record : records) {
                br.write(record);
                br.newLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Method for getting time of current record from table
     */
    @Override
    public Integer getRecordTime(int level) {
        String[] record = recordTimeMap.get(level);

        String recordTimeStr;

        if (record != null) {
            recordTimeStr = record[TIME_INDEX];
            return Integer.parseInt(recordTimeStr);
        }

        return null;
    }

    private void initializeCurrentRecords() {
        FileReader fileReader;
        try {
            fileReader = new FileReader(Paths.get("").toAbsolutePath() + File.separator + RECORDS_FILE);
            readAndSetRecords(fileReader);
        } catch (FileNotFoundException e) {
            for (int i = 0; i < MAX_COUNT_OF_RECORDS; i++) {
                recordTimeMap.put(i, null);
            }
        }
    }

    /**
     * Method for reading and adding to records table, else record value is null
     *
     * @param fileReader records file reader
     */
    private void readAndSetRecords(FileReader fileReader) {
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            for (int level = 0; level < MAX_COUNT_OF_RECORDS; level++) {
                line = br.readLine();
                if (line != null && !line.isEmpty()) {
                    String[] nameAndTime = line.split("-");
                    highRecordWindow.updateHighRecord(
                            nameAndTime[NAME_INDEX],
                            Integer.parseInt(nameAndTime[TIME_INDEX]),
                            level
                    );

                    recordTimeMap.put(level, nameAndTime);

                } else {
                    recordTimeMap.put(level, null);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }

    private String validateName(String name) {
        String[] nameParts = name.split("\\s+");

        if (nameParts.length > MAX_PARTS_OF_NAME) {
            name = nameParts[0].trim() + " " + nameParts[1].trim() + " " + nameParts[2].trim();
        }

        if (name.length() > MAX_LENGTH_NAME) {
            name = name.substring(0, MAX_LENGTH_NAME);
        }

        return name;
    }
}
