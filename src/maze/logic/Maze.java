package maze.logic;

import java.util.Scanner;

public class Maze
{
	char matrix[][] = new char[10][10];
	Hero sirWilliam = new Hero();
	Dragon fm = new Dragon();
	boolean finished = false;

	
	public boolean getFinished()
	{
		return finished;
	}
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

		if (matrix[newY][newX] == 'X')
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

		matrix[oldY][oldX] = ' ';

		sirWilliam.setX(newX);
		sirWilliam.setY(newY);

		matrix[newY][newX] = sirWilliam.getChar();
	}

	public void updateGame()
	{
		//moveHero(3, 0);
		//moveHero(0, 4);
		//moveHero(1, 0);
		int[] dx = new int[1];
		int[] dy = new int[1];
		dx[0] = 0;
		dy[0] = 0;

		readInput(dx,dy);
		System.out.println("Dx: " + dx[0] + "  Dy: " + dy[0]);
		moveHero(dx[0],dy[0]);
		print();
		dx[0] = 0;
		dy[0] = 0;
	}

	public void readInput(int[] dx, int[] dy)
	{
		Scanner s = new Scanner(System.in);

		if(s.hasNextLine())
		{
			char dir = s.next().charAt(0);
			if(dir == 'S' || dir == 's')
				dy[0] = 1;
			else if (dir == 'N' || dir == 'n')
				dy[0] = -1;
			else if (dir == 'A' || dir == 'a')
				dx[0] = -1;
			else if (dir == 'D' || dir == 'd')
				dx[0] = 1;

		}
		s.close();

	}

	public static void main(String[] args) 
	{
		Maze myMaze = new Maze();

		while(!myMaze.getFinished())
		{
			myMaze.updateGame();
		}
	}

}


