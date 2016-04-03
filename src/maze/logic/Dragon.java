package maze.logic;

public class Dragon extends Character 
{
	boolean sleep;
	/**
	 * Creates a new Dragon on Point 1,3
	 */
	public Dragon()
	{
		this(1, 3);
	}
	
	/**
	 * Creates a new Dragon on the coordinates indicated by parameters
	 * @param  x the x coordinate where the dragon will be placed
	 * @param  y the y coordinate where the dragon will be placed 
	 */
	public Dragon(int x, int y)
	{
		super(x, y);
		this.visual = 'D';
		this.sleep = false;
	}
	
	/**
	 * Returns true if dragon is sleeping, false otherwise
	 * @return true if asleep, false otherwise
	 */
	public boolean getSleep()
	{
		return sleep;
	}
	
	/**
	 * Sets a new sleep status and updates the char
	 * @param  sleep the new sleep value
	 */
	public void setSleep(boolean sleep)
	{
		this.sleep = sleep;
		if(this.sleep)
			this.visual = 'd';
		else
			this.visual = 'D';
	}

}
