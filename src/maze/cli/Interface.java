package maze.cli;

import java.util.Scanner;

public class Interface {

	public Interface() {}

	public static void print(char[][] matrix)
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

	public static int readInput()
	{
		Scanner s = new Scanner(System.in);

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

	public static int chooseMode()
	{

		Scanner s = new Scanner(System.in);

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

}
