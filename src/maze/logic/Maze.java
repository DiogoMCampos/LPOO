package maze.logic;

public class Maze 
{
	char matrix[][] = new char[10][10];
	Hero sirWilliam = new Hero();
	Dragon fm = new Dragon();
	
	public boolean finished = false;

	public Maze()
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				if (i == 0 || i == matrix.length - 1 || j == 0 || j == matrix.length - 1) 
				{
					matrix[i][j] = 'X';
				}
				
				else if (((i > 1 && i < 5) || i == 6 || i == 7) && (j == 2 || j == 3 || j == 5 || j == 7))
				{
						matrix[i][j] = 'X';
				}
				
				else if (i == 5 && j == 7)
				{
					matrix[i][j] = 'X';
				}
				
				else if (i == 8 && (j == 2 || j == 3) )
				{
					matrix[i][j] = 'X';
				}
				
				else
					matrix[i][j] = ' ';	
			}
		}
		
		matrix[1][1] = sirWilliam.getChar();
		matrix[3][1] = fm.getChar();
		matrix[8][1] = 'E';
		matrix[6][9] = 'S';
		
		this.print();
	}
	
	public void print()
	{
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				if (j == matrix[i].length - 1)
					System.out.println(matrix[i][j]);
				else
					System.out.print(matrix[i][j] + " ");
			}
		}
		
		System.out.println();
	}
	
	public void moveHero(int dx, int dy) 
	{
		int newX = sirWilliam.getX() + dx;
		int newY = sirWilliam.getY() + dy;
		int dragonX = fm.getX();
		int dragonY = fm.getY();
		
		if (matrix[newX][newY] == 'X')
			return;
		
		if (Math.abs(dragonX - newX) <= 1 && Math.abs(dragonY - newY) <= 1)
		{
			if (!sirWilliam.getSword())
			{
				sirWilliam.dies();
				this.finished = true;
				return;
			}
			
			fm.dies();
		}
		
		if (newX == 9 && newY == 6)
		{
			if (!fm.life)
			{
				this.finished = true;
			}
			
			return;
		}
		
		updateHeroPosition(newX, newY);
	}
	
	public void updateHeroPosition(int newX, int newY)
	{
		int oldX = sirWilliam.getX();
		int oldY = sirWilliam.getY();
		
		matrix[oldX][oldY] = ' ';
		
		sirWilliam.setX(newX);
		sirWilliam.setY(newY);
		
		matrix[newX][newY] = sirWilliam.getChar();
	}
	
	public void updateGame()
	{
		moveHero(0, 3);
		moveHero(4, 0);
		moveHero(0, 1);
		print();
	}
	
	public static void main(String[] args) 
	{
		Maze myMaze = new Maze();
		
		myMaze.updateGame();
	}

}
