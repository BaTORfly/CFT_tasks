package ru.shift.model.timer;

public interface GameTimerModel {

    void startTimer();

    void stopTimer();

    void resetTimer();

    int getCurrentSeconds();
}
