package maze.logic;

import java.util.ArrayList;
import java.util.Random;

import maze.cli.*;

public class Maze
{
	Interface textInterface = new Interface();
	char matrix[][] = new char[10][10];
	Hero hero = new Hero();
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
					hero = new Hero(j, i);
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
	
	public int dragonsAlive()
	{
		int num = 0;
		for(int i = 0; i < dragons.size(); i++)
		{
			if(dragons.get(i).getLife())
				num++;
		}
		
		return num;
	}

	public Point getHeroPosition() 
	{
		return hero.getPosition();
	}

	public char getHeroChar()
	{
		return hero.getChar();
	}

	public boolean getHeroLife()
	{
		return hero.getLife();
	}

	public void setMode(int mode)
	{
		this.mode = mode;
	}
	

	public void moveHero(int dx, int dy) 
	{
		int newX = hero.getX() + dx;
		int newY = hero.getY() + dy;

		if (matrix[newY][newX] == 'X')
			return;

		if (matrix[newY][newX] == 'S')
		{
			if (dragonsAlive() == 0)
			{
				this.finished = true;
				updateHeroPosition(newX, newY);
				textInterface.msgWinGame();
			}
			return;
		}

		if (matrix[newY][newX] == 'E') 
		{
			if (!hero.getSword())
			{
				hero.setSword();
				textInterface.msgGetSword();
			}
		}

		updateHeroPosition(newX, newY);

		for (int i = 0; i < dragons.size(); i++) 
		{
			heroDragonCollisions(dragons.get(i));
		}
	}
	
	public void heroDragonCollisions(Dragon currentDragon) 
	{
		int dragonX = currentDragon.getX();
		int dragonY = currentDragon.getY();
		
		if (hero.isAdjacent(currentDragon) && currentDragon.getLife())
		{
			if (!hero.getSword() && !currentDragon.getSleep())
			{
				hero.dies();
				int sY = hero.getY();
				int sX = hero.getX();
				if (hero.getPosition().equals(currentDragon.getPosition()))
					matrix[sY][sX] = 'D';
				else
					matrix[sY][sX] = ' ';
				textInterface.msgHeroDied();
				this.finished = true;
				return;
			}

			else if(hero.getSword())
			{
				currentDragon.dies();
				if (!hero.getPosition().equals(currentDragon.getPosition()))
					matrix[dragonY][dragonX] = ' ';
				textInterface.msgDragonDies();
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
			
			char newPosition = matrix[dragonY + dy][dragonX + dx];
			
			if(newPosition == 'X' || newPosition == 'S' || newPosition == 'D' || newPosition == 'd')
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
		int oldX = hero.getX();
		int oldY = hero.getY();

		matrix[oldY][oldX] = ' ';

		hero.setX(newX);
		hero.setY(newY);

		matrix[newY][newX] = hero.getChar();
	}
	
	public void interUpdateGame() {
		int movement = textInterface.readInput();
		updateGame(movement);
		textInterface.print(matrix);
	}

	public void updateGame(int movement)
	{	
		
		for (int i = 0; i < dragons.size(); i++)
		{
			Dragon currentDragon = dragons.get(i);
			if (currentDragon.getLife())
			{
				if (mode == 2)
					sleepDragon(currentDragon);
				if(mode != 0 && !currentDragon.getSleep() && decideMove())
					moveDragon(currentDragon);
			}
		}

		if (movement == 0)
			moveHero(0, -1);
		else if (movement == 1)
			moveHero(-1, 0);
		else if (movement == 2)
			moveHero(0, 1);
		else
			moveHero(1, 0);
	}

	public void playGame()
	{
		this.mode = textInterface.chooseMode() - 1;

		textInterface.print(this.matrix);

		while(!getFinished())
		{
			interUpdateGame();
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


