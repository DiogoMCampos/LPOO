package maze.logic;

public class Point 
{
	
	int x, y;
	
	public Point(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public boolean isAdjacent(Point comparable)
	{
		if ((Math.abs(this.x - comparable.getX()) <= 1 && this.y == comparable.getY()) ||
				(Math.abs(this.y - comparable.getY()) <= 1 && this.x == comparable.getX()))
			return true;

		return false;
	}
}
