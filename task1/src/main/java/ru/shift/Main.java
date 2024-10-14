package ru.shift;

import java.util.Scanner;

public class Main {
    private static final char CELL_SEPARATOR = '|';
    private static final String DASH = "-";
    private static final char DASH_SEPARATOR = '+';
    private static final String SPACE_BAR = " ";
    /**
     * Метод для корректного получения целочисленного числа от 1 до 32 с консоли
     * @return корректно введёное число
     */
    private static int getValidTableSize()
    {
        Scanner cons = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.println("Введите размер таблицы умножения от 1 до 32");
            if(cons.hasNextInt()) {
                userInput = cons.nextInt();
                if (userInput >= 1 && userInput <= 32) {
                    break;
                } else {
                    System.out.println("Ошибка! Число должно быть в диапазоне от 1 до 32. Попробуйте снова:");
                }
            } else {
                System.out.println("Ошибка! Вы должны ввести целочисленное число. Попробуйте снова:");
                cons.next();
            }
        }
        return userInput;
    }
    /**
     * метод получения "межстрочной" строки
     * @param tableSize размер таблицы
     * @param firstColumnWidth ширина первого столбца
     * @param cellWidth ширина клетки
     * @return "межстрочная строка"
     */
    private static String getBetweenTheLines(int tableSize, int firstColumnWidth, int cellWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(DASH.repeat(firstColumnWidth));
        String dashForCell = DASH.repeat(cellWidth);
        for (int i = 1; i <= tableSize; i++) {
            sb.append(DASH_SEPARATOR);
            sb.append(dashForCell);
        }
        return sb.toString();
    }

    /**
     * Метод получения числа в клетке
     * @param cellWidth ширина клетки
     * @param number число
     * @return строка с пробелами, для подгона числа под клетку
     */
    private static String getNumberInCell(int cellWidth, int number){
        return SPACE_BAR.repeat(cellWidth - String.valueOf(number).length()) + String.valueOf(number);
    }
    /**
     * Метод вывода таблицы умножения в консоль
     * @param tableSize размер таблицы
     */
    private static void tableOutput(int tableSize)
    {
        StringBuilder tableLine = new StringBuilder();
        int cellWidth = String.valueOf(tableSize * tableSize).length();
        int firstColumnWidth = String.valueOf(tableSize).length();
        String betweenTheLines = getBetweenTheLines(tableSize, firstColumnWidth, cellWidth);

        tableLine.append(SPACE_BAR.repeat(firstColumnWidth));

        for (int i = 1; i <= tableSize; i++) {
            tableLine.append(CELL_SEPARATOR).append(getNumberInCell(cellWidth, i));
        }
        tableLine.append("\n").append(betweenTheLines).append("\n");
        System.out.print(tableLine);
        tableLine.setLength(0);

        for (int i = 1; i <= tableSize ; i++) {
            tableLine.append(getNumberInCell(firstColumnWidth, i));
            for (int j = 1; j <= tableSize; j++) {
                tableLine.append(CELL_SEPARATOR).append(getNumberInCell(cellWidth, i * j));
            }
            tableLine.append("\n").append(betweenTheLines).append("\n");
            System.out.print(tableLine);
            tableLine.setLength(0);
        }
    }

    public static void main(String[] args) {
        int tableSize = getValidTableSize();
        tableOutput(tableSize);
    }
}