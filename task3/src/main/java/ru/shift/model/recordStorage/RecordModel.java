package ru.shift.model.recordStorage;

public interface RecordModel {

    void setNewRecord(String recordName);

    Integer getRecordTime(int difficulty);

    void setTimeRecord(int timeRecord);

    void setLevelRecord(int levelRecord);
}