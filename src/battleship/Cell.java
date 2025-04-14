package battleship;

public class Cell {
    private Ship ship;
    private boolean isHit;

    public Cell() {
        this.ship = null;
        this.isHit = false;
    }

    public void placeShip(Ship ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public Ship getShip() {
        return ship;
    }

    public void markHit() {
        this.isHit = true;
    }

    public boolean isHit() {
        return isHit;
    }
}

