package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final ShipType type;
    private final List<int[]> coordinates;
    private final List<Boolean> hitTracker;

    public Ship(ShipType type, int startRow, int startCol, String orientation) {
        this.type = type;
        this.coordinates = new ArrayList<>();
        this.hitTracker = new ArrayList<>();

        switch (type) {
            case DESTROYER:
                coordinates.add(new int[]{startRow, startCol});
                coordinates.add(new int[]{startRow, startCol + 1});
                coordinates.add(new int[]{startRow + 1, startCol});
                coordinates.add(new int[]{startRow + 1, startCol + 1});
                break;

            case SUBMARINE:
                if (orientation.equals("DR")) {
                    for (int i = 0; i < 3; i++) {
                        coordinates.add(new int[]{startRow + i, startCol + i});
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        coordinates.add(new int[]{startRow + i, startCol - i});
                    }
                }
                break;

            case CRUISER:
                if (orientation.equals("H")) {
                    for (int i = 0; i < 3; i++) {
                        coordinates.add(new int[]{startRow, startCol + i});
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        coordinates.add(new int[]{startRow + i, startCol});
                    }
                }
                break;

            default:
                break;
        }

        for (int i = 0; i < coordinates.size(); i++) {
            hitTracker.add(false);
        }
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    public boolean registerHit(int row, int col) {
        for (int i = 0; i < coordinates.size(); i++) {
            int[] pos = coordinates.get(i);
            if (pos[0] == row && pos[1] == col) {
                hitTracker.set(i, true);
                return true;
            }
        }
        return false;
    }

    public boolean isSunk() {
        for (boolean hit : hitTracker) {
            if (!hit) return false;
        }
        return true;
    }
}
