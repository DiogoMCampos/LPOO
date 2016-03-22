package maze.logic;

import java.util.ArrayList;
import java.util.Random;

import maze.cli.*;

public class Maze
{
	Interface textInterface = new Interface();
	char matrix[][] = new char[10][10];
	Hero sirWilliam = new Hero();
	ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	boolean finished = false;
	int mode;

	public boolean getFinished()
	{
		return finished;
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
				{
					Dragon newDragon = new Dragon(j, i);
					dragons.add(newDragon);
				}
			}
		}
	}
	
	public char[][] getMaze()
	{
		return matrix;
	}
	
	public ArrayList<Dragon> getDragons()
	{
		return dragons;
	}

	public Point getHeroPosition() 
	{
		return sirWilliam.getPosition();
	}
/*
	public Point getDragonPosition()
	{
		return fm.getPosition();
	}*/

	public char getHeroChar()
	{
		return sirWilliam.getChar();
	}
	/*
	public char getDragonChar()
	{
		return fm.getChar();
	}*/

	public boolean getHeroLife()
	{
		return sirWilliam.getLife();
	}
/*
	public boolean getDragonLife()
	{
		return fm.getLife();
	} */
	
	public boolean allDragonsDead() 
	{
		for (int i = 0; i < dragons.size(); i++)
		{
			if (dragons.get(i).getLife())
				return false;
		}
		
		return true;
	}

	public void moveHero(int dx, int dy) 
	{
		int newX = sirWilliam.getX() + dx;
		int newY = sirWilliam.getY() + dy;

		if (matrix[newY][newX] == 'X')
			return;

		if (matrix[newY][newX] == 'S')
		{
			if (allDragonsDead())
			{
				this.finished = true;
				updateHeroPosition(newX, newY);
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

		for (int i = 0; i < dragons.size(); i++) 
		{
			Dragon currentDragon = dragons.get(i);
			int dragonX = currentDragon.getX();
			int dragonY = currentDragon.getY();
			
			if (sirWilliam.isAdjacent(currentDragon) && currentDragon.getLife())
			{
				if (!sirWilliam.getSword() && !currentDragon.getSleep())
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
					currentDragon.dies();
					matrix[dragonY][dragonX] = ' ';
					textInterface.msgDragonDies();
				}
			}
		}
	}

	public boolean sleepDragon(Dragon dragon)
	{
		Random rand = new Random();
		int sleep = rand.nextInt(3);

		if(sleep == 0) // change status
		{
			if(dragon.getSleep())
			{	
				dragon.setSleep(false);
				textInterface.msgDragonAwake();
			}
			else
			{
				dragon.setSleep(true);
				textInterface.msgDragonSleep();
			}
		}


		int dragonX = dragon.getX();
		int dragonY = dragon.getY();
		matrix[dragonY][dragonX] = dragon.getChar();
		
		return dragon.getSleep();
	}

	public boolean decideMove()
	{
		Random rand = new Random();
		int move = rand.nextInt(5);

		if(move == 0)
			return false;
		else
			return true;
	}
	public void moveDragon(Dragon dragon)
	{
		int dx = 0;
		int dy = 0;
		int dragonX = dragon.getX();
		int dragonY = dragon.getY();

		boolean validMovement = true;

		do
		{
			Random rand = new Random();
			int move = rand.nextInt(4);

			if(move == 0) // move right
				dx = 1;
			else if(move == 1) // move left
				dx = -1;
			else if(move == 2) // move up
				dy = -1;
			else if(move == 3) // move down
				dy = 1;
			
			if(matrix[dragonY + dy][dragonX + dx] == 'X' || matrix[dragonY + dy][dragonX + dx] == 'S')
			{
				validMovement = false;
				dx = 0;
				dy = 0;
			}
			else
				validMovement = true;
			
			
		}while(!validMovement);


	

		if(matrix[dragonY + dy][dragonX + dx] == 'E')
		{
			matrix[dragonY][dragonX] = ' ';
			matrix[dragonY + dy][dragonX + dx] = 'F';

			dragon.setX(dragonX + dx);
			dragon.setY(dragonY + dy);
			return;
		}

		if(matrix[dragonY + dy][dragonX + dx] == ' ' && matrix[dragonY][dragonX] == 'F')
		{
			matrix[dragonY][dragonX] = 'E';
			matrix[dragonY + dy][dragonX + dx] = dragon.getChar();

			dragon.setX(dragonX + dx);
			dragon.setY(dragonY + dy);
			return;
		}

		matrix[dragonY][dragonX] = ' ';

		dragon.setX(dragonX + dx);
		dragon.setY(dragonY + dy);

		matrix[dragonY + dy][dragonX + dx] = dragon.getChar();

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
			for (int i = 0; i < dragons.size(); i++)
				sleepDragon(dragons.get(i));
			
		for (int i = 0; i < dragons.size(); i++)
		{
			Dragon currentDragon = dragons.get(i);
			if(!currentDragon.getSleep() && mode != 1 && decideMove())
				moveDragon(currentDragon);
		}
		
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

	public void playGame()
	{
		this.mode = textInterface.chooseMode();

		textInterface.print(this.matrix);

		while(!getFinished())
		{
			updateGame();
		}

	}

	public void endInterface()
	{
		textInterface.finalize();
	}

	public static void main(String[] args) 
	{
		MazeBuilder mb = new MazeBuilder(17, 0);
		
		char [][] m3 = mb.getMaze();
		
		Maze myMaze = new Maze(m3);
		
		myMaze.playGame();

		myMaze.endInterface();
	}

}


