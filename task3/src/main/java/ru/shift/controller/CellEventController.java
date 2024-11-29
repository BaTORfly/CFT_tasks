package ru.shift.controller;

import lombok.RequiredArgsConstructor;
import ru.shift.model.gameField.CellEventModel;
import ru.shift.view.listeners.CellEventListener;
import ru.shift.view.main.ButtonType;

@RequiredArgsConstructor
public class CellEventController implements CellEventListener {

    private final CellEventModel cellEventModel;

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        switch (buttonType) {
            case LEFT_BUTTON -> cellEventModel.openClosedCell(x, y);
            case RIGHT_BUTTON -> cellEventModel.toggleFlag(x, y);
            case MIDDLE_BUTTON -> cellEventModel.openClosedCellsAroundWithoutFlags(x, y);
        }
    }
}
