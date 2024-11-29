package ru.shift.model.gameField;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameState {
    NOT_STARTED,
    PLAYING,
    WIN,
    BOMBED
}
