package ru.shift.model.gameField.listeners;


import ru.shift.model.gameField.CellImage;

public interface CellEventModelListener {
    void changeBombsCount(int count);

    void changeCellIcon(int x, int y, CellImage image);

    void changeGameField(int rows, int cols, int bombsCount);
}
