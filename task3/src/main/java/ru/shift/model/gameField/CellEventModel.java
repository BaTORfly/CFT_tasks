package ru.shift.model.gameField;

public interface CellEventModel {

    void openClosedCell(int x, int y);

    void toggleFlag(int x, int y);

    void openClosedCellsAroundWithoutFlags(int x, int y);
}
