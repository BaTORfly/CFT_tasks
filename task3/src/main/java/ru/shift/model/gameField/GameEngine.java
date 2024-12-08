package ru.shift.model.gameField;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.shift.model.gameField.listeners.CellEventModelListener;
import ru.shift.model.gameField.listeners.SettingsModelListener;
import ru.shift.model.gameField.listeners.WinAndLoseListener;
import ru.shift.model.recordStorage.RecordModel;
import ru.shift.model.timer.GameTimerModel;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class GameEngine implements CellEventModel, SettingsModel {

    private final CellEventModelListener cellEventModelListener;
    private final SettingsModelListener settingsModelListener;
    private final WinAndLoseListener recordsListener;
    private final WinAndLoseListener winListener;
    private final WinAndLoseListener loseListener;

    private final GameTimerModel gameTimerModel;
    private final RecordModel recordModel;

    @Setter
    private GameState currentState = GameState.NOT_STARTED;
    private Cell[][] field;
    private int flaggedCellsCount = 0;
    private int closedCellsCount = 0;
    private LevelSettings lastLevelSettings = LevelSettings.NOVICE;
    private final Random random = new Random();


    @Override
    public void selectNewLevel(LevelSettings levelSettings) {
        if (levelSettings == null) {
            levelSettings = lastLevelSettings;
        } else {
            lastLevelSettings = levelSettings;
        }

        selectLevel();
        settingsModelListener.changeLevel(levelSettings);
    }
    //left mouse click
    @Override
    public void openClosedCell(int x, int y) {
        if (currentState == GameState.NOT_STARTED) {
            openInNotStartedState(x, y);
            changeBombsCount();
        } else if (currentState == GameState.PLAYING) {
            openInPlayingState(x, y);

            if (currentState != GameState.PLAYING) {
                return;
            }
            changeBombsCount();
        }
    }
    // right mouse click
    @Override
    public void toggleFlag(int x, int y) {
        if (currentState == GameState.BOMBED || currentState == GameState.WIN) {
            return;
        }

        Cell cell = field[y][x];

        if (cell.isOpened()) {
            return;
        }

        if (!cell.isFlagged() && flaggedCellsCount < lastLevelSettings.getBombsCount()) {
            cell.setFlagged(true);
            flaggedCellsCount++;
        } else if (cell.isFlagged()) {
            cell.setFlagged(false);
            flaggedCellsCount--;
        }

        changeCellImage(cell);
        changeBombsCount();
    }

    //middle mouse click
    @Override
    public void openClosedCellsAroundWithoutFlags(int x, int y) {
        if (currentState != GameState.PLAYING) {
            return;
        }

        Cell cell = field[y][x];

        if (!cell.isOpened() || getBombsCountAround(cell) == 0) {
            return;
        }

        int flaggedCount = getFlagedCountAround(cell);
        int closedCount = getClosedCountAround(cell);

        if (flaggedCount != getBombsCountAround(cell) || closedCount == flaggedCount) {
            return;
        }

        for (Cell around : getCellsAround(cell)) {
            if (around.isFlagged() && !around.isOpened()) {
                if (!around.isBomb()) {
                    gameOver();
                    return;
                }
                continue;
            }

            if (canOpenCell(around)) {
                openCell(around);

                if (currentState != GameState.PLAYING) {
                    return;
                }

                if (getBombsCountAround(around) == 0) {
                    openCellsAround(around);
                }
            }
        }

        if (currentState == GameState.PLAYING) {
            changeBombsCount();
        }
    }

    private void selectLevel() {
        initField();
        closedCellsCount = lastLevelSettings.getRows() * lastLevelSettings.getCols() - lastLevelSettings.getBombsCount();
        currentState = GameState.NOT_STARTED;
        cellEventModelListener.changeGameField(
                lastLevelSettings.getRows(),
                lastLevelSettings.getCols(),
                lastLevelSettings.getBombsCount()
        );
        resetData();
        gameTimerModel.stopTimer();
        gameTimerModel.resetTimer();
    }

    private void initField() {
        int rows = lastLevelSettings.getRows();
        int cols = lastLevelSettings.getCols();

        field = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                field[row][col] = new Cell(
                        row,
                        col,
                        false,
                        false,
                        false
                );
            }
        }
    }

    private void openInNotStartedState(int x, int y) {
        placeBombs(x, y);
        currentState = GameState.PLAYING;
        openInPlayingState(x, y);
        gameTimerModel.startTimer();
    }

    private void placeBombs(int startX, int startY) {

        Cell startCell = field[startY][startX];
        int rows = lastLevelSettings.getRows();
        int cols = lastLevelSettings.getCols();

        List<Cell> bombCells = new ArrayList<>(cols * rows - 1);
        bombCells.add(startCell);

        for (int i = 0; i < lastLevelSettings.getBombsCount(); i++) {
            Cell cell = field[random.nextInt(rows)][random.nextInt(cols)];
            if (!bombCells.contains(cell)) {
                cell.setBomb(true);
                bombCells.add(cell);
            }
        }
    }

    private void openInPlayingState(int x, int y) {
        Cell cellToOpen = field[y][x];
        if (!cellToOpen.isFlagged() && !cellToOpen.isBomb()) {

            if (!canOpenCell(cellToOpen) || cellToOpen.isFlagged()) {
                return;
            }

            openCell(cellToOpen);

            if (currentState != GameState.PLAYING) {
                return;
            }

            if (getBombsCountAround(cellToOpen) == 0) {
                openCellsAround(cellToOpen);
            }
        } else
            gameOver();

    }

    private boolean canOpenCell(Cell cellToOpen) {
        return !cellToOpen.isBomb() && !cellToOpen.isOpened();
    }

    private void openCell(Cell cellToOpen) {
        cellToOpen.setOpened(true);
        changeCellImage(cellToOpen);

        if (cellToOpen.isFlagged()) {
            flaggedCellsCount--;
        }

        closedCellsCount--;

        if (closedCellsCount == 0) {
            gameWon();
        }
    }

    private void openCellsAround(Cell cell) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(cell);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            for (Cell around : getCellsAround(current)) {
                if (canOpenCell(around)) {
                    openCell(around);

                    if (currentState != GameState.PLAYING) {
                        return;
                    }

                    if (getBombsCountAround(around) == 0) {
                        queue.add(around);
                    }
                }
            }
        }
    }

    private int getBombsCountAround(Cell cell) {
        int bombsCount = 0;
        List<Cell> arounds = getCellsAround(cell);

        for (Cell around : arounds) {
            if (around.isBomb()) {
                bombsCount++;
            }
        }

        return bombsCount;
    }

    private List<Cell> getCellsAround(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        for (int dirRow = -1; dirRow <= 1; dirRow++) {
            for (int dirCol = -1; dirCol <= 1; dirCol++) {
                if (dirRow == 0 && dirCol == 0) {
                    continue;
                }

                int neighborRow = cell.getRow() + dirRow;
                int neighborCol = cell.getCol() + dirCol;

                if (inField(neighborRow, neighborCol)) {
                    neighbors.add(field[neighborRow][neighborCol]);
                }
            }
        }

        return neighbors;
    }

    private boolean inField(int row, int col) {
        return row >= 0 && row < lastLevelSettings.getRows() &&
                col >= 0 && col < lastLevelSettings.getCols();
    }


    private int getFlagedCountAround(Cell cell){
        int flagCount = 0;
        for (Cell around : getCellsAround(cell)) {
            if (!around.isOpened() && around.isFlagged()) {
                flagCount++;
            }
        }
        return flagCount;
    }

    private int getClosedCountAround(Cell cell){
        int closedCount = 0;
        for (Cell around : getCellsAround(cell)) {
            if (!around.isOpened()) {
                closedCount++;
            }
        }
        return closedCount;
    }

    private void gameOver() {
        gameTimerModel.stopTimer();
        currentState = GameState.BOMBED;
        showAllBombs();
        resetData();
        loseListener.showContent();
    }

    private void gameWon() {
        gameTimerModel.stopTimer();
        currentState = GameState.WIN;
        showAllBombs();
        resetData();

        int currentDifficulty = lastLevelSettings.getLevel();
        int currentTime = gameTimerModel.getCurrentSeconds();

        Integer currentRecord = recordModel.getRecordTime(currentDifficulty - 1);

        if (currentRecord == null || currentTime < currentRecord) {
            recordModel.setLevelRecord(currentDifficulty - 1);
            recordModel.setTimeRecord(currentTime);
            recordsListener.showContent();
        }

        winListener.showContent();
    }

    private void showAllBombs() {
        for (Cell[] rows : field) {
            for (Cell cell : rows) {
                if (cell.isBomb()) {
                    cellEventModelListener.changeCellIcon(cell.getCol(), cell.getRow(), CellImage.BOMB);
                }
            }
        }
    }

    private void changeCellImage(Cell cell) {
        CellImage image;

        if (cell.isOpened()) {
            image = CellImage.getCellImage(String.valueOf(getBombsCountAround(cell)));
        } else if (cell.isFlagged()) {
            image = CellImage.FLAG;
        } else {
            image = CellImage.CLOSED;
        }

        cellEventModelListener.changeCellIcon(cell.getCol(), cell.getRow(), image);
    }

    private void changeBombsCount() {
        cellEventModelListener.changeBombsCount(lastLevelSettings.getBombsCount() - flaggedCellsCount);
    }

    private void resetData() {
        flaggedCellsCount = 0;
    }
}
