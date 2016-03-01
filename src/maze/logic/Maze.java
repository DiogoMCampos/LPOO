package maze.logic;

import java.util.Random;
import java.util.Scanner;

import maze.cli.*;

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

		Interface.print(matrix);
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
			if (!sirWilliam.getSword() && !fm.getSleep())
			{
				sirWilliam.dies();
				int sY = sirWilliam.getY();
				int sX = sirWilliam.getX();
				matrix[sY][sX] = ' ';
				System.out.println("Oh no, Sir William just died!");
				this.finished = true;
				return;
			}

			else if(sirWilliam.getSword())
			{
				fm.dies();
				System.out.println("The dragon has been killed!");
			}
		}

		if (newX == 9 && newY == 6)
		{
			if (!fm.life)
			{
				this.finished = true;
				System.out.println("Congratulations, you just won the game!");
			}

			return;
		}

		if (newX == 1 && newY == 8) 
		{
			if (!sirWilliam.getSword())
			{
				sirWilliam.setSword();
				System.out.println("Sir William just got an amazing sword!");
			}
		}

		updateHeroPosition(newX, newY);
	}

	public void sleepDragon()
	{
		Random rand = new Random();
		int sleep = rand.nextInt(3);

		if(sleep == 0) // change status
		{
			if(fm.getSleep())
			{	
				fm.setSleep(false);
				System.out.println("Be careful, the dragon has awoken!");
			}
			else
			{
				fm.setSleep(true);
				System.out.println("The dragon fell asleep!");
			}
		}


		int dragonX = fm.getX();
		int dragonY = fm.getY();
		matrix[dragonY][dragonX] = fm.getChar();
	}

	public void moveDragon()
	{
		Random rand = new Random();
		int move = rand.nextInt(5);

		int dx = 0;
		int dy = 0;
		int dragonX = fm.getX();
		int dragonY = fm.getY();

		if(move == 1) // move right
			dx = 1;
		else if(move == 2) // move left
			dx = -1;
		else if(move == 3) // move up
			dy = -1;
		else if(move == 4) // move down
			dy = 1;

		if(matrix[dragonY + dy][dragonX + dx] == 'X')
			return;

		if(matrix[dragonY + dy][dragonX + dx] == 'E')
		{
			matrix[dragonY][dragonX] = ' ';
			matrix[dragonY + dy][dragonX + dx] = 'F';

			fm.setX(dragonX + dx);
			fm.setY(dragonY + dy);
			return;
		}

		if(matrix[dragonY + dy][dragonX + dx] == ' ' && matrix[dragonY][dragonX] == 'F')
		{
			matrix[dragonY][dragonX] = 'E';
			matrix[dragonY + dy][dragonX + dx] = fm.getChar();

			fm.setX(dragonX + dx);
			fm.setY(dragonY + dy);
			return;
		}

		matrix[dragonY][dragonX] = ' ';

		fm.setX(dragonX + dx);
		fm.setY(dragonY + dy);

		matrix[dragonY + dy][dragonX + dx] = fm.getChar();

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
		sleepDragon();
		
		if(!fm.getSleep())
			moveDragon();
		
		int movement = Interface.readInput();
		
		if (movement == 0)
			moveHero(0, -1);
		else if (movement == 1)
			moveHero(-1, 0);
		else if (movement == 2)
			moveHero(0, 1);
		else
			moveHero(1, 0);
		
		Interface.print(matrix);
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


