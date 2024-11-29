package ru.shift.model.gameField;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public final class Cell {
    private int row;
    private int col;
    private boolean isBomb;
    private boolean flagged;
    private boolean isOpened;
}
