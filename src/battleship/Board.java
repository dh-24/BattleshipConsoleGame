package battleship;

import java.util.*;

public class Board {
    private static final int SIZE = 10;
    private final Cell[][] grid;
    private final List<Ship> ships;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        ships = new ArrayList<>();

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = new Cell();
    }

    public void placeShipsManually(Scanner scanner) {
        for (ShipType type : ShipType.values()) {
            boolean placed = false;
            while (!placed) {
                System.out.println("Place your " + type + ":");
                String input = Utils.promptForInput(scanner, "Enter starting coordinate (e.g., B4): ");
                int[] coords = Utils.parseCoords(input);

                String orientation = Utils.promptForInput(scanner, "Enter orientation (H, V, DR, DL): ").toUpperCase();
                placed = placeShip(type, coords[0], coords[1], orientation);
                if (!placed) System.out.println("Invalid placement. Try again.");
            }
        }
    }

    public void placeShipsRandomly() {
        for (ShipType type : ShipType.values()) {
            boolean placed = false;
            while (!placed) {
                int row = (int) (Math.random() * SIZE);
                int col = (int) (Math.random() * SIZE);
                String orientation = Utils.getRandomOrientation();
                placed = placeShip(type, row, col, orientation);
            }
        }
    }

    public boolean placeShip(ShipType type, int startRow, int startCol, String orientation) {
        Ship ship = new Ship(type, startRow, startCol, orientation);
        for (int[] c : ship.getCoordinates())
            if (!Utils.inBounds(c[0], c[1]) || grid[c[0]][c[1]].hasShip()) return false;

        for (int[] c : ship.getCoordinates()) grid[c[0]][c[1]].placeShip(ship);
        ships.add(ship);
        return true;
    }

    public boolean fireAt(int row, int col) {
        grid[row][col].markHit();
        return grid[row][col].hasShip() && grid[row][col].getShip().registerHit(row, col);
    }

    public boolean alreadyTried(int row, int col) {
        return grid[row][col].isHit();
    }

    public boolean allShipsSunk() {
        for (Ship s : ships) if (!s.isSunk()) return false;
        return true;
    }

    public void print(boolean hideShips) {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                Cell cell = grid[i][j];
                if (cell.isHit()) System.out.print(cell.hasShip() ? "X " : "O ");
                else if (cell.hasShip() && !hideShips) System.out.print("S ");
                else System.out.print("~ ");
            }
            System.out.println();
        }
    }
}


