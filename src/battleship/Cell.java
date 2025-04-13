package battleship;

public class Cell {

	// cell properties
	
	private final int row;
	private final int col;
	private boolean hasShip;
	private boolean isHit;
	
	public Cell(int row, int col)
	{
		this.row = row;
		this.col = col;
		this.hasShip = false;
		this.isHit = false;
	}
	
	// getters and setters
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public boolean hasShip()
	{
		return hasShip;
	}
	
	public void setShip(boolean hasShip)
	{
		this.hasShip = hasShip;
	}
	
	public boolean isHit()
	{
		return isHit;
	}
	
	public void setHit(boolean isHit)
	{
		this.isHit = isHit;
	}
	
	
}
