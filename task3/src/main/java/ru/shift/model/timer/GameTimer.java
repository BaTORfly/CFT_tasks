package ru.shift.model.timer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.shift.model.timer.listeners.GameTimerListener;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Модель-таймер (игровой таймер)
 */
@RequiredArgsConstructor
public class GameTimer implements GameTimerModel {

    private Timer timer;
    @Getter
    private int currentSeconds;
    private boolean isRunning;

    private final GameTimerListener mainWindow;

    @Override
    public void startTimer() {
        if (!isRunning) {
            isRunning = true;
            timer = new Timer();
            Instant start = Instant.now();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Instant update = Instant.now();
                    currentSeconds = (int) Duration.between(start, update).toSeconds();
                    mainWindow.onTimerValueUpdated(currentSeconds);
                }
            }, 1000, 1000);
        }
    }

    @Override
    public void stopTimer() {
        if (isRunning) {
            timer.cancel();
            isRunning = false;
        }
    }

    @Override
    public void resetTimer() {
        currentSeconds = 0;
        mainWindow.onTimerValueUpdated(currentSeconds);
    }
}
