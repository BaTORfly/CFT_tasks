package ru.shift.model.recordStorage;

import java.io.IOException;

public interface RecordModel {

    void setNewRecord(String recordName) throws IOException;

    Integer getRecordTime(int difficulty);

    void setTimeRecord(int timeRecord);

    void setLevelRecord(int levelRecord);
}