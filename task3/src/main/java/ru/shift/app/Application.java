package ru.shift.app;

import ru.shift.controller.CellEventController;
import ru.shift.controller.SettingsController;
import ru.shift.controller.RecordController;
import ru.shift.model.gameField.GameEngine;
import ru.shift.model.gameField.LevelSettings;
import ru.shift.model.recordStorage.RecordStorage;
import ru.shift.model.timer.GameTimerModel;
import ru.shift.model.timer.GameTimer;
import ru.shift.view.main.MainWindow;
import ru.shift.view.others.HighRecordWindow;
import ru.shift.view.others.LoseWindow;
import ru.shift.view.others.RecordsWindow;
import ru.shift.view.others.WinWindow;
import ru.shift.view.settings.SettingsWindow;

public class Application {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        RecordsWindow recordsWindow = new RecordsWindow(mainWindow);
        HighRecordWindow highRecordWindow = new HighRecordWindow(mainWindow);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        WinWindow winWindow = new WinWindow(mainWindow);

        GameTimerModel gameTimerModel = new GameTimer(mainWindow);
        RecordStorage recordStorageModel = new RecordStorage(highRecordWindow);
        GameEngine gameEngine = new GameEngine(
                mainWindow,
                settingsWindow,
                recordsWindow,
                winWindow,
                loseWindow,
                gameTimerModel,
                recordStorageModel
        );

        CellEventController cellEventController = new CellEventController(gameEngine);
        SettingsController settingsController = new SettingsController(gameEngine);
        RecordController recordController = new RecordController(recordStorageModel);

        mainWindow.setCellListener(cellEventController);
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setHighScoresMenuAction(e -> highRecordWindow.setVisible(true));
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setNewGameMenuAction(e -> gameEngine.selectNewLevel(null));

        settingsWindow.setGameTypeListener(settingsController);
        recordsWindow.setNameListener(recordController);

        loseWindow.setNewGameListener(e -> gameEngine.selectNewLevel(null));
        loseWindow.setExitListener(e -> mainWindow.dispose());
        winWindow.setNewGameListener(e -> gameEngine.selectNewLevel(null));
        winWindow.setExitListener(e -> mainWindow.dispose());

        gameEngine.selectNewLevel(LevelSettings.NOVICE);
        mainWindow.setVisible(true);
    }
}
