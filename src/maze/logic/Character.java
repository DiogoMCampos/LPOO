package maze.logic;

public class Character 
{
	int x, y;
	boolean life;
	char visual;
	
	public Character()
	{
		this(0, 0);
	}
	
	public Character(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.life = true;
	}
	
	public char getChar()
	{
		return this.visual;
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
	
	public void dies()
	{
		this.life = false;
		this.visual = ' ';
	}
}
