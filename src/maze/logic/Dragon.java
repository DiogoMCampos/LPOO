package maze.logic;

public class Dragon extends Character 
{
	
	public Dragon()
	{
		this(1, 3);
	}
	
	public Dragon(int x, int y)
	{
		super(x, y);
		this.visual = 'D';
	}

}
