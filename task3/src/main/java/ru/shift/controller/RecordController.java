package ru.shift.controller;

import lombok.RequiredArgsConstructor;

import ru.shift.model.recordStorage.RecordModel;
import ru.shift.view.listeners.RecordListener;

import java.io.IOException;

@RequiredArgsConstructor
public class RecordController implements RecordListener {

    private final RecordModel recordModel;

    @Override
    public void setNewRecord(String name) throws IOException {
        recordModel.setNewRecord(name);
    }
}
