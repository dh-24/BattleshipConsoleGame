package battleship;

import java.util.ArrayList;
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
			if (orientation.equalsIgnoreCase("LR")) {
                    coordinates.add(new int[]{startRow, startCol});
                    coordinates.add(new int[]{startRow + 1, startCol + 1});
                    coordinates.add(new int[]{startRow + 2, startCol + 2});
                } else if (orientation.equalsIgnoreCase("RL")) {
                    coordinates.add(new int[]{startRow, startCol});
                    coordinates.add(new int[]{startRow + 1, startCol - 1});
                    coordinates.add(new int[]{startRow + 2, startCol - 2});
                }
                break;

			
		case CRUISER:
			// straight line 3 cells
			if (orientation.equalsIgnoreCase("H")) {
				coordinates.add(new int[]{startRow, startCol});
				coordinates.add(new int[]{startRow, startCol + 1});
				coordinates.add(new int[]{startRow, startCol + 2});
			} else if (orientation.equalsIgnoreCase("V")) {
				coordinates.add(new int[]{startRow, startCol});
				coordinates.add(new int[]{startRow + 1, startCol});
				coordinates.add(new int[]{startRow + 2, startCol});
			}
		
			break;
	}
	 // Initialize hit tracker to all false
	 for (int i = 0; i < coordinates.size(); i++) {
		hitTracker.add(new boolean[]{false});
	}
}

public ShipType getType() {
	return type;
}

	public List<int[]> getCoordinates() 
	{
		return coordinates;
	}
	
	public boolean isHit(int row, int col) {
        for (int i = 0; i < coordinates.size(); i++) {
            int[] coord = coordinates.get(i);
            if (coord[0] == row && coord[1] == col) {
                hitTracker.get(i)[0] = true;
                return true;
            }
        }
        return false;
    }

    public boolean isSunk() {
        for (boolean[] hit : hitTracker) {
            if (!hit[0]) return false;
        }
        return true;
    }
}


