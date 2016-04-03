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

	/**
	 * Returns the status of the game
	 * @return true if finished, false otherwise
	 */
	public boolean getFinished()
	{
		return finished;
	}

	/**
	 * Initializes the Maze by reading a matrix
	 * @param matrix the matrix in which the maze is based
	 */
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
	
	/**
	 * Returns the maze
	 * @return the maze in a bidimensional array
	 */
	public char[][] getMaze()
	{
		return matrix;
	}
	
	/**
	 * Returns the list of the dragons contained in the maze
	 * @return an ArrayList containing the dragons
	 */
	public ArrayList<Dragon> getDragons()
	{
		return dragons;
	}
	
	/**
	 * Returns the number of dragons alive
	 * @return the number of dragons alive
	 */
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

	/**
	 * Returns the hero position
	 * @return the Point where the Hero is located
	 */
	public Point getHeroPosition() 
	{
		return hero.getPosition();
	}

	/**
	 * Returns the Hero's char
	 * @return the Hero's char
	 */
	public char getHeroChar()
	{
		return hero.getChar();
	}

	/**
	 * Returns the hero's life
	 * @return true if hero's alive, false otherwise
	 */
	public boolean getHeroLife()
	{
		return hero.getLife();
	}

	/**
	 * Sets the game mode
	 * @param mode the mode to set
	 */
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	/**
	 * Moves the hero according to the parameters and checks if there are any 
	 * changes such as hero picking the sword, if the hero reached the exit
	 * @param dx the displacement on x axis
	 * @param dy the displacement on y axis
	 */
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
	
	/**
	 * Checks and handles if there are any collisions between dragons and the hero
	 */
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

	/**
	 * Changes the dragon sleep status with a probability of 1/3
	 * @param dragon the dragon to change sleep status
	 * @return the new sleep status of the dragon
	 */
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

	/**
	 * Decides if a dragon will move or not
	 * The probability of moving is 4/5 and the probability of not moving is 1/5
	 * @return the X position of the character
	 */
	public boolean decideMove()
	{
		Random rand = new Random();
		int move = rand.nextInt(5);

		if(move == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Moves a dragon and handles collisions with the sword
	 * @param dragon the dragon that will move
	 */
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

	/**
	 * Clears the actual position where the dragon is and fills the new one
	 * @param newX the new X of the hero
	 * @param newY the new Y of the hero
	 */
	public void updateHeroPosition(int newX, int newY)
	{
		int oldX = hero.getX();
		int oldY = hero.getY();

		matrix[oldY][oldX] = ' ';

		hero.setX(newX);
		hero.setY(newY);

		matrix[newY][newX] = hero.getChar();
	}
	
	/**
	 * Reads user input from the text Interface and
	 * updates the game accordingly and prints the updated matrix
	 */
	public void interUpdateGame() {
		int movement = textInterface.readInput();
		updateGame(movement);
		textInterface.print(matrix);
	}

	/**
	 * Coordinates the game by moving the dragons, changing their sleep state
	 * and moving the hero using the parameter received
	 * @param movement code received by readInput
	 */
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

	/**
	 * Reads the game mode, prints the matrix and asks for inputs
	 */
	public void playGame()
	{
		this.mode = textInterface.chooseMode() - 1;

		textInterface.print(this.matrix);

		while(!getFinished())
		{
			interUpdateGame();
		}

	}

	/**
	 * Ends the textInterface used
	 */
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


