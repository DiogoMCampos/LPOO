package maze.cli;

import java.util.Scanner;

public class Interface {

	Scanner s = new Scanner(System.in);
	public Interface() {}

	/**
	 * Prints the matrix to the console
	 * @param matrix the bidimensional array to print
	 */
	public void print(char[][] matrix)
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

	/**
	 * Reads the user input to move the hero
	 * @return 0 if upper movement, 1 if left movement, 2 if down movement and 3 if right movement
	 */
	public int readInput()
	{
		String direction;

		do {
			System.out.println("Move Sir William with WASD keys: ");
			direction = s.next();
		} while (!(direction.equals("w") || direction.equals("a") || direction.equals("s") || direction.equals("d")));

		//s.close();

		if (direction.equals("w")) 
			return 0;
		else if (direction.equals("a"))
			return 1;
		else if (direction.equals("s"))
			return 2;
		else 
			return 3;
	}

	/**
	 * Reads user input to choose the game mode
	 * @return 1 if the chosen mode was no movement, 2 if random movement and 3 if random movement and sleep
	 */
	public int chooseMode()
	{
		int direction = 0;

		do 
		{
			System.out.println("Please insert the desired dragon behaviour: ");
			System.out.println("1 - No movement \n2 - Random movement \n3 - Random movement and sleep \n");

			if(s.hasNextInt())
				direction = s.nextInt();
			else
				s.next();
		} while (direction != 1 && direction != 2 && direction != 3);
		
		return direction;
	}
	
	/**
	 * Prints the message about hero's death to console
	 */
	public void msgHeroDied()
	{
		System.out.println("Oh no, Sir William just died!");
	}
	
	/**
	 * Prints the message about dragon's death to console
	 */
	public void msgDragonDies()
	{
		System.out.println("The dragon has been killed!");
	}
	
	/**
	 * Prints the message when the player wins the game to console
	 */
	public void msgWinGame()
	{
		System.out.println("Congratulations, you just won the game!");
	}
	
	/**
	 * Prints the message about the hero getting a sword
	 */
	public void msgGetSword()
	{
		System.out.println("Sir William just got an amazing sword!");
	}
	
	/**
	 * Prints the message on the wake of the dragon
	 */
	public void msgDragonAwake()
	{
		System.out.println("Be careful, the dragon has awoken!");
	}
	
	/**
	 * Prints the message on the sleep of the dragon
	 */
	public void msgDragonSleep()
	{
		System.out.println("The dragon fell asleep!");
	}
	
	/**
	 * Closes the scanner used to read the inputs
	 */
	public void finalize()
	{
		s.close();
	}
}
