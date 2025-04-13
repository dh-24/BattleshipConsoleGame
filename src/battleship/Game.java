package battleship;

import java.util.Scanner;
import java.util.Random;

public class Game {

	private final Board playerBoard;
	private final Board computerBoard;
	private final Scanner scanner;
	private final Random random;
	
	public Game()
	{
		this.playerBoard = new Board();
		this.computerBoard = new Board();
		this.scanner = new Scanner();
		this.random = new Random();
	}
	
	public void start()
	{
		System.out.println("\n==============================");
		System.out.println("Welcome to the Battleship Game!");
		System.out.println("==============================");
		System.out.println("\nLet's begin by placing your ships on the 10x10 board.");
		
		// player and computer place their ships
		
		playerBoard.placeShipsManually(scanner);
		computerBoard.placeShipsRandomly();
		
		boolean gameOver = false;
		
		while (!gameOver)
		{
			System.out.println("\n---- Your turn: ----");
			playerBoard.print(false); // show players board
			computerBoard.print(true); // hide computer ships
			
			boolean playerGetsAnotherTurn;
			
			do 
			{
				System.out.println("Enter a target (Ex: A4): ");
				String input = scanner.nextLine().trim().toUpperCase();
				int[] coords = Utils.parseCoords(input);
				
				// check if coordinates are within bounds of board
				
				if (coords == null || !Utils.inBounds(coords[0], coords[1]))
				{
					System.out.println("Bad input. Try again.");
					playerGetsAnotherTurn = true; // ask them again
					continue;
				}
				
				int row = coords[0];
				int col = coords[1];
				
				if (computerBoard.alreadyTried(row, col))
				{
					System.out.println("You already fired at this location.");
					playerGetsAnotherTurn = true;
					continue;
				}
				
				boolean hit = computerBoard.fireAt(row, col);
				System.out.println(hit ? "Hit!" : "Miss!");
				playerGetsAnotherTurn = hit;
				
				
				if (computerBoard.allShipsSunk())
				{
					System.out.println("Congrats! You sank all the enemy's ships. YOU WIN!");
					gameOver = true;
					break;
				}
				
			} while (playerGetsAnotherTurn);
			
			// exit and end game if player wins
			
			if (gameOver) break;
			
			
			
			// computers turn
			
			System.out.println("\n---- Computer's turn: ----");
			
			boolean computerGetsAnotherTurn;
			
			do {
				int row = random.nextInt(10);
				int col = random.nextInt(10);
				
				if (playerBoard.alreadyTried(row, col))
				{
					computerGetsAnotherTurn = true;
					continue;
				}
				
				System.out.println("Computer fired at: " + Utils.coordsToString(row, col));
				boolean hit = playerBoard.fireAt(row, col);
				System.out.println(hit ? "Hit!" : "Miss!");
				computerGetsAnotherTurn = hit;
				
				if (playerBoard.allShipsSunk())
				{
					System.out.println("\nThe computer sunk all of your ships. You lost!");
					gameOver = true;
					break;
				}
				
			} while (computerGetsAnotherTurn);
			
		}
		
		// game over status
		
		System.out.println("\nFinal Boards: ");
		System.out.println("Your board:");
		playerBoard.print(false);
		System.out.println("Computer's board:");
		computerBoard.print(false);
		
	}
	
}
