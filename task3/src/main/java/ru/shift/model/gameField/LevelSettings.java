package ru.shift.model.gameField;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LevelSettings {
    NOVICE(1, 9, 9, 10),
    MEDIUM(2, 12, 12, 32),
    EXPERT(3, 16, 30, 99);

    private final int level;
    private final int rows;
    private final int cols;
    private final int bombsCount;
}
