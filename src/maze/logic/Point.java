package maze.logic;

public class Point
{

	int x, y;

	/**
	 * Creates a new Point on position 0,0
	 */
	public Point()
	{
		this(0, 0);
	}

	/**
	 * Creates a new Point on the coordinates indicated by parameters
	 * @param  x the x coordinate
	 * @param  y the y coordinate
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns x value
	 * @return the x value of the Point
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Returns y value
	 * @return the y value of the Point
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Sets a new x value
	 * @param x the new x
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * Sets a new y value
	 * @param y the new y
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * Checks if the Point is adjacent to another
	 * @param  comparable the Point to compare
	 * @return true if the Points are adjacent, false otherwise
	 */
	public boolean isAdjacent(Point comparable)
	{
		if ((Math.abs(this.x - comparable.getX()) <= 1 && this.y == comparable.getY()) ||
				(Math.abs(this.y - comparable.getY()) <= 1 && this.x == comparable.getX()))
			return true;

		return false;
	}

	/**
	 * Checks if the Point is in the same position as another
	 * @param  comparable the character to compare
	 * @return true if the Points are in the same position, false otherwise
	 */
	@Override
	public boolean equals(Object comparable)
	{
		if (this.getClass() != comparable.getClass())
			return false;

		Point p2 = (Point) comparable;
		return (this.x == p2.getX() && this.y == p2.getY());
	}
}
