package maze.logic;

import java.util.Random;

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

	public Point getHeroPosition() 
	{
		return sirWilliam.getPosition();
	}

	public Point getDragonPosition()
	{
		return fm.getPosition();
	}

	public char getHeroChar()
	{
		return sirWilliam.getChar();
	}
	
	public char getDragonChar()
	{
		return fm.getChar();
	}

	public boolean getHeroLife()
	{
		return sirWilliam.getLife();
	}

	public boolean getDragonLife()
	{
		return fm.getLife();
	}

	public void moveHero(int dx, int dy) 
	{
		int newX = sirWilliam.getX() + dx;
		int newY = sirWilliam.getY() + dy;
		int dragonX = fm.getX();
		int dragonY = fm.getY();

		if (matrix[newY][newX] == 'X')
			return;

		if (matrix[newY][newX] == 'S')
		{
			if (!fm.life)
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

		if (sirWilliam.isAdjacent(fm) && fm.getLife())
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
				matrix[dragonY][dragonX] = ' ';
				textInterface.msgDragonDies();
			}
		}
	}

	public boolean sleepDragon()
	{
		Random rand = new Random();
		int sleep = rand.nextInt(3);

		if(sleep == 0) // change status
		{
			if(fm.getSleep())
			{	
				setDragonSleep(false);
				textInterface.msgDragonAwake();
			}
			else
			{
				setDragonSleep(true);
				textInterface.msgDragonSleep();
			}
		}


		int dragonX = fm.getX();
		int dragonY = fm.getY();
		matrix[dragonY][dragonX] = fm.getChar();
		
		return fm.getSleep();
	}
	
	public void setDragonSleep(boolean state)
	{
		fm.setSleep(state);
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
	public void moveDragon()
	{
		int dx = 0;
		int dy = 0;
		int dragonX = fm.getX();
		int dragonY = fm.getY();

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

		if(!fm.getSleep() && (mode == 2 || mode == 3) && decideMove())
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
		MazeBuilder mb = new MazeBuilder(8);
		
		char [][] m3 = mb.getMaze();
		
		Maze myMaze = new Maze(m3);
		
		myMaze.playGame();

		myMaze.endInterface();
	}

}


