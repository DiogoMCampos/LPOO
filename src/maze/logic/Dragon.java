package maze.logic;

public class Dragon extends Character 
{
	
	public Dragon()
	{
		this(3, 1);
	}
	
	public Dragon(int x, int y)
	{
		super(x, y);
		this.visual = 'D';
	}

}
