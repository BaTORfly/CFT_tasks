package ru.shift.view.listeners;

import ru.shift.view.main.ButtonType;

public interface CellEventListener {
    void onMouseClick(int x, int y, ButtonType buttonType);
}
