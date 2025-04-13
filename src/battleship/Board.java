package battleship;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private final Cell[][] grid; // 10x10 grid of cells
	private final List<Ship> ships; // initialize list to store the ships
	
	public Board()
	{
		grid = new Cell[10][10];
		ships = new ArrayList<>();
		
		// fill grid with new Cell objects (contain a cell, col)
		
		for (int row = 0; row < 10; row++)
		{
			for (int col = 0; col < 10; col++)
			{
				grid[row][col] = new Cell(row, col);
			}
		}
	}
	
	

}
