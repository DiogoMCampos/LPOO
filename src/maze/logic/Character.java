package maze.logic;

public class Character 
{
	Point position;
	boolean life;
	char visual;
	
	public Character()
	{
		this(0, 0);
	}
	
	public Character(int x, int y)
	{
		this.position = new Point(x, y);
		this.life = true;
	}
	
	public char getChar()
	{
		return this.visual;
	}
	
	public int getX()
	{
		return position.getX();
	}
	
	public int getY()
	{
		return position.getY();
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void setX(int x)
	{
		this.position.setX(x);
	}
	
	public void setY(int y)
	{
		this.position.setY(y);
	}
	
	public void dies()
	{
		this.life = false;
		this.visual = ' ';
	}
	
	public boolean isAdjacent(Character comparable)
	{
		return this.position.isAdjacent(comparable.getPosition());
	}
	
	public boolean getLife()
	{
		return life;
	}
}
