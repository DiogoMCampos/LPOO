package maze.logic;

public class Dragon extends Character 
{
	boolean sleep;
	public Dragon()
	{
		this(1, 3);
	}
	
	public Dragon(int x, int y)
	{
		super(x, y);
		this.visual = 'D';
		this.sleep = false;
	}
	
	public boolean getSleep()
	{
		return sleep;
	}
	public void setSleep(boolean sleep)
	{
		this.sleep = sleep;
		if(this.sleep)
			this.visual = 'd';
		else
			this.visual = 'D';
	}

}
