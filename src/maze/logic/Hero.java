package maze.logic;

public class Hero extends Character 
{
	boolean sword;
	/**
	 * Creates a new Hero on Point 1,1
	 */
	public Hero()
	{
		this(1, 1);
	}
	
	/**
	 * Creates a new Hero on the coordinates indicated by parameters
	 * @param  x the x coordinate where the Hero will be placed
	 * @param  y the y coordinate where the Hero will be placed 
	 */
	public Hero(int x, int y)
	{
		super(x, y);
		this.visual = 'H';
		this.sword = false;
	}
	
	/**
	 * Returns true if hero has the sword, false otherwise
	 * @return true if hero has the sword, false otherwise
	 */
	public boolean getSword()
	{
		return this.sword;
	}
	
	/**
	 * Adds the sword to hero and updates the char
	 */
	public void setSword()
	{
		this.sword = true;
		this.visual = 'A';
	}
}
