package ru.shift.controller;

import lombok.RequiredArgsConstructor;

import ru.shift.model.recordStorage.RecordModel;
import ru.shift.view.listeners.RecordListener;

@RequiredArgsConstructor
public class RecordController implements RecordListener {

    private final RecordModel recordModel;

    @Override
    public void setNewRecord(String name) {
        recordModel.setNewRecord(name);
    }
}
