package maze.logic;

import java.util.Random;
import java.util.Scanner;

import maze.cli.*;

public class Maze
{
	Interface textInterface = new Interface();
	char matrix[][] = new char[10][10];
	Hero sirWilliam = new Hero();
	Dragon fm = new Dragon();
	boolean finished = false;
	int mode;

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

	}
	
	public Maze(char[][] matrix)
	{
		this.matrix = matrix;
		
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				if (matrix[i][j] == 'H')
					sirWilliam = new Hero(j, i);
				else if (matrix[i][j] == 'D')
					fm = new Dragon(j, i);
			}
		}
	}

	public void moveHero(int dx, int dy) 
	{
		int newX = sirWilliam.getX() + dx;
		int newY = sirWilliam.getY() + dy;
		int dragonX = fm.getX();
		int dragonY = fm.getY();

		if (matrix[newY][newX] == 'X')
			return;
		/////////////////////*********************///////////////////////////
		/////////////////////*********  **********///////////////////////////
		/////////////////////*******      ********///////////////////////////
		/////////////////////*****          ******///////////////////////////
		// TODO Mudar esta verificação para o fim, tirar condição da diagonal
		/////////////////////*****          ******///////////////////////////
		/////////////////////*******      ********///////////////////////////
		/////////////////////*********  **********///////////////////////////
		/////////////////////*********************///////////////////////////
		if (Math.abs(dragonX - newX) <= 1 && Math.abs(dragonY - newY) <= 1)
		{
			if (!sirWilliam.getSword() && !fm.getSleep())
			{
				sirWilliam.dies();
				int sY = sirWilliam.getY();
				int sX = sirWilliam.getX();
				matrix[sY][sX] = ' ';
				textInterface.msgHeroDied();
				this.finished = true;
				return;
			}

			else if(sirWilliam.getSword())
			{
				fm.dies();
				textInterface.msgDragonDies();
			}
		}

		if (matrix[newY][newX] == 'S')
		{
			if (!fm.life)
			{
				this.finished = true;
				textInterface.msgWinGame();
			}

			return;
		}

		if (matrix[newY][newX] == 'E') 
		{
			if (!sirWilliam.getSword())
			{
				sirWilliam.setSword();
				textInterface.msgGetSword();
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
				textInterface.msgDragonAwake();
			}
			else
			{
				fm.setSleep(true);
				textInterface.msgDragonSleep();
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
		if(mode == 3)
			sleepDragon();

		if(!fm.getSleep() && (mode == 2 || mode == 3))
			moveDragon();

		int movement = textInterface.readInput();

		if (movement == 0)
			moveHero(0, -1);
		else if (movement == 1)
			moveHero(-1, 0);
		else if (movement == 2)
			moveHero(0, 1);
		else
			moveHero(1, 0);

		textInterface.print(matrix);
	}


	public static void main(String[] args) 
	{
		char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', 'H', 'S'},
				{'X', ' ', 'X', ' ', 'X'},
				{'X', 'E', ' ', 'D', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
		
		Maze myMaze = new Maze(m1);

		myMaze.mode = textInterface.chooseMode();

		textInterface.print(myMaze.matrix);
		
		while(!myMaze.getFinished())
		{
			myMaze.updateGame();
		}
	}

}


