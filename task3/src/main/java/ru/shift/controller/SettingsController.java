package ru.shift.controller;

import lombok.RequiredArgsConstructor;
import ru.shift.model.gameField.LevelSettings;
import ru.shift.model.gameField.SettingsModel;
import ru.shift.view.listeners.SettingsListener;
import ru.shift.view.settings.GameType;

@RequiredArgsConstructor
public class SettingsController implements SettingsListener {

    private final SettingsModel difficultyModel;

    @Override
    public void setLevel(GameType type) {
        LevelSettings levelSettings = switch (type) {
            case NOVICE -> LevelSettings.NOVICE;
            case MEDIUM -> LevelSettings.MEDIUM;
            case EXPERT -> LevelSettings.EXPERT;
        };

        difficultyModel.selectNewLevel(levelSettings);
    }
}
