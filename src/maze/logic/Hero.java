package maze.logic;

public class Hero extends Character 
{
	boolean sword;
	public Hero()
	{
		this(1, 1);
	}
	
	public Hero(int x, int y)
	{
		super(x, y);
		this.visual = 'H';
		this.sword = false;
	}
	
	public boolean getSword()
	{
		return this.sword;
	}
	
	public void setSword()
	{
		this.sword = true;
		this.visual = 'A';
	}
}
