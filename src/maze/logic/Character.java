package maze.logic;

public class Character 
{
	Point position;
	boolean life;
	char visual;
	
	/**
	 * Creates a character at position(0,0)
	 */
	public Character()
	{
		this(0, 0);
	}
	
	/**
	 * Creates a character at the position on arguments
	 * @param  x the position on x axis of the character
	 * @param  y the position on y axis of the character
	 */
	public Character(int x, int y)
	{
		this.position = new Point(x, y);
		this.life = true;
	}
	
	/**
	 * Returns the char associated to the character
	 * @return the char associated to the character
	 */
	public char getChar()
	{
		return this.visual;
	}
	
	/**
	 * Returns the X position of the character
	 * @return the X position of the character
	 */
	public int getX()
	{
		return position.getX();
	}
	
	/**
	 * Returns the Y position of the character
	 * @return the Y position of the character
	 */
	public int getY()
	{
		return position.getY();
	}
	
	/**
	 * Returns the Point where the character is
	 * @return the Point where the character is
	 */
	public Point getPosition()
	{
		return position;
	}
	
	/**
	 * Sets a new X position
	 * @param  x the new X 
	 */
	public void setX(int x)
	{
		this.position.setX(x);
	}
	
	/**
	 * Sets a new Y position
	 * @param  y the new Y
	 */
	public void setY(int y)
	{
		this.position.setY(y);
	}
	
	/**
	 * Sets the life attribute to false and the char associated to a white space
	 */
	public void dies()
	{
		this.life = false;
		this.visual = ' ';
	}
	
	/**
	 * Checks if the character is adjacent to another
	 * @param  comparable the character to compare
	 * @return true if the characters are adjacent, false otherwise
	 */
	public boolean isAdjacent(Character comparable)
	{
		return this.position.isAdjacent(comparable.getPosition());
	}
	
	/**
	 * Returns the life of character as a boolean
	 * @return the life of character as a boolean
	 */
	public boolean getLife()
	{
		return life;
	}
}
