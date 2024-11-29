package ru.shift.model.gameField;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum CellImage {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    FLAG("flag"),
    BOMB("bomb"),
    CLOSED("cell");

    private final String nameImage;

    private static final Map<String, CellImage> IMAGE_MAP = new HashMap<>(CellImage.values().length);

    static {
        for (CellImage value : CellImage.values()) {
            IMAGE_MAP.put(value.nameImage, value);
        }
    }

    public static CellImage getCellImage(String imageName) {
        return IMAGE_MAP.get(imageName);
    }
}
