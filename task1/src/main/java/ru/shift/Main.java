package ru.shift;

import java.util.Scanner;

public class Main {
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
        sb.append("-".repeat(firstColumnWidth));
        String dashForCell = "-".repeat(cellWidth);
        for (int i = 1; i <= tableSize; i++) {
            sb.append("+");
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
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(cellWidth - String.valueOf(number).length()));
        sb.append(number);
        return sb.toString();
    }
    /**
     * Метод вывода таблицы умножения в консоль
     * @param tableSize размер таблицы
     */
    private static void tableOutput(int tableSize)
    {
        StringBuilder table = new StringBuilder();
        char cellSeparator = '|';
        int cellWidth = String.valueOf(tableSize * tableSize).length();
        int firstColumnWidth = String.valueOf(tableSize).length();
        String betweenTheLines = getBetweenTheLines(tableSize, firstColumnWidth, cellWidth);

        table.append(" ".repeat(firstColumnWidth));

        for (int i = 1; i <= tableSize; i++) {
            table.append(cellSeparator).append(getNumberInCell(cellWidth, i));
        }
        table.append("\n").append(betweenTheLines).append("\n");

        for (int i = 1; i <= tableSize ; i++) {
            table.append(getNumberInCell(firstColumnWidth, i));
            for (int j = 1; j <= tableSize; j++) {
                table.append(cellSeparator).append(getNumberInCell(cellWidth, i * j));
            }
            table.append("\n").append(betweenTheLines).append("\n");
        }
        System.out.println(table);
    }

    public static void main(String[] args) {
        int tableSize = getValidTableSize();
        tableOutput(tableSize);
    }
}