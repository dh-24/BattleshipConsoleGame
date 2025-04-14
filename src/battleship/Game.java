package battleship;

import java.util.Scanner;
import java.util.Random;

public class Game {
    private final Board playerBoard;
    private final Board computerBoard;
    private final Scanner scanner;
    private final Random random;

    public Game() {
        this.playerBoard = new Board();
        this.computerBoard = new Board();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void start() {
        System.out.println("\n==============================");
        System.out.println("Welcome to the Battleship Game!");
        System.out.println("==============================");

        playerBoard.placeShipsManually(scanner);
        computerBoard.placeShipsRandomly();

        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("\n---- Your turn: ----");
            playerBoard.print(false);
            computerBoard.print(true);

            boolean playerGetsAnotherTurn;
            do {
                String input = Utils.promptForInput(scanner, "Enter a target (e.g., A4): ");
                int[] coords = Utils.parseCoords(input);

                if (coords == null || !Utils.inBounds(coords[0], coords[1])) {
                    System.out.println("Invalid coordinate. Try again.");
                    playerGetsAnotherTurn = true;
                    continue;
                }

                if (computerBoard.alreadyTried(coords[0], coords[1])) {
                    System.out.println("You already tried that spot.");
                    playerGetsAnotherTurn = true;
                    continue;
                }

                boolean hit = computerBoard.fireAt(coords[0], coords[1]);
                System.out.println(hit ? "Hit!" : "Miss!");
                playerGetsAnotherTurn = hit;

                if (computerBoard.allShipsSunk()) {
                    System.out.println("🎉 You win! All enemy ships are sunk.");
                    gameOver = true;
                    break;
                }
            } while (playerGetsAnotherTurn);

            if (gameOver) break;

            System.out.println("\n---- Computer's turn: ----");

            boolean computerGetsAnotherTurn;
            do {
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                if (playerBoard.alreadyTried(row, col)) {
                    computerGetsAnotherTurn = true;
                    continue;
                }

                System.out.println("Computer fired at: " + Utils.coordsToString(row, col));
                boolean hit = playerBoard.fireAt(row, col);
                System.out.println(hit ? "Hit!" : "Miss!");
                computerGetsAnotherTurn = hit;

                if (playerBoard.allShipsSunk()) {
                    System.out.println("💥 The computer wins! All your ships are sunk.");
                    gameOver = true;
                    break;
                }
            } while (computerGetsAnotherTurn);
        }

        System.out.println("\nFinal Boards:");
        System.out.println("Your Board:");
        playerBoard.print(false);
        System.out.println("Computer's Board:");
        computerBoard.print(false);
    }
}

