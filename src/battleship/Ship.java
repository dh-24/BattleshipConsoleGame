package battleship;

import java.util.List;

public class Ship {
	
	private final ShipType type;
	private final List<int[]> coordinates;
	
	public Ship(ShipType type, int startRow, int startCol, String orientation)
	{
		this.type = type;
		this.coordinates = new ArrayList<>();
		
		switch (type)
		{
		case DESTROYER:
			// 2x2 square
			coordinates.add(new int[] {startRow, startCol});
			coordinates.add(new int[] {startRow, startCol + 1});
			coordinates.add(new int[] {startRow + 1, startCol});
			coordinates.add(new int[] {startRow + 1, startCol + 1});
		
		case SUBMARINE:
			// diagonal 3 cells
			
		case CRUISER:
			// straight line 3 cells
		}
		break;
	}
	
	public List<int[]> getCoordinates() 
	{
		return coordinates;
	}
	
	

}


