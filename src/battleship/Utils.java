package battleship;

import java.util.Scanner;

public class Utils {
    public static final int SIZE = 10;

    public static int[] parseCoords(String input) {
        input = input.toUpperCase().trim();
        if (input.length() < 2 || input.length() > 3) return null;

        char rowChar = input.charAt(0);
        int row = rowChar - 'A';
        String colStr = input.substring(1);

        int col;
        try {
            col = Integer.parseInt(colStr) - 1;
        } catch (NumberFormatException e) {
            return null;
        }

        if (!inBounds(row, col)) return null;
        return new int[]{row, col};
    }

    public static boolean inBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public static String coordsToString(int row, int col) {
        return "" + (char) ('A' + row) + (col + 1);
    }

    public static String promptForInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static String getRandomOrientation() {
        String[] options = {"H", "V", "DR", "DL"};
        return options[(int) (Math.random() * options.length)];
    }
}
