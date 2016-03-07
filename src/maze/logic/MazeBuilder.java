package maze.logic;

import maze.cli.*;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder {

	char maze[][];
	boolean[][] mazeDone;
	Stack<Point> mazeHistory = new Stack<Point>();
	int mazeSize;
	Point currentPoint;
	Point currentPointBool;
	
	Interface inter = new Interface();

	public char[][] getMaze()
	{
		return maze;
	}

	// restriction: size has to be an odd number > 5
	public MazeBuilder(int size)
	{
		this.mazeSize = size;
		maze = new char[size][size];
		mazeDone = new boolean[(size - 1) / 2][(size - 1) / 2];

		
		
		if (size % 2 == 0)
		{
			size++;
		}

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				maze[i][j] = 'X';
		
		
		for (int i = 0; i < mazeDone.length; i++)
			for (int j = 0; j < mazeDone.length; j++)
				mazeDone[i][j] = false;

		// set exit and generate starting point
		Point startingPoint = genStartingPoint();

		currentPoint = startingPoint;
		currentPointBool = new Point((currentPoint.getX() - 1) / 2, (currentPoint.getY() - 1) / 2);

		mazeHistory.push(new Point(currentPointBool.getX(), currentPointBool.getY()));

		mazeDone[currentPointBool.getY()][currentPointBool.getX()] = true;

		while (!mazeHistory.empty())
		{
			boolean[] directions = new boolean[4];
			boolean movementPossible = false;
			boolean missingTests = true;
			int newDirection;
			Random rand = new Random();
			
			//inter.print(maze);
			//printSmallM();
			do 
			{
				do 
				{
					newDirection = rand.nextInt(4);
				} while (directions[newDirection]);

				directions[newDirection] = true;

				movementPossible = isMovementPossible(newDirection);

				missingTests = false;
				for (int i = 0; i < 4; i++)
					if (!directions[i])
						missingTests = true;

			} while (!movementPossible && missingTests);

			
			if (!movementPossible)
			{
				Point p = mazeHistory.pop();
				currentPointBool = new Point(p.getX(), p.getY());
				currentPoint.setX((currentPointBool.getX() * 2) + 1);
				currentPoint.setY((currentPointBool.getY() * 2) + 1);
				
			}
			else
			{
				int xMovement = 0;
				int yMovement = 0;

				switch (newDirection) {
				case 0:
					xMovement = -1;
					break;
				case 1:
					xMovement = 1;
					break;
				case 2:
					yMovement = -1;
					break;
				case 3:
					yMovement = 1;
					break;
				}

				currentPointBool.setX(currentPointBool.getX() + xMovement);
				currentPointBool.setY(currentPointBool.getY() + yMovement);
				mazeDone[currentPointBool.getY()][currentPointBool.getX()] = true;
				mazeHistory.push(new Point(currentPointBool.getX(), currentPointBool.getY()));

				currentPoint.setX(currentPoint.getX() + xMovement);
				currentPoint.setY(currentPoint.getY() + yMovement);
				maze[currentPoint.getY()][currentPoint.getX()] = ' ';
				currentPoint.setX(currentPoint.getX() + xMovement);
				currentPoint.setY(currentPoint.getY() + yMovement);
				maze[currentPoint.getY()][currentPoint.getX()] = ' ';
			}
		} while (!mazeHistory.empty());
	}

	private Point genStartingPoint()
	{
		Random rand = new Random();
		int genSide = rand.nextInt(4);
		int genLimit = (mazeSize - 1) / 2;
		int index = ((rand.nextInt(genLimit) + 1) * 2) - 1;
		Point startingPoint = new Point();

		if (genSide == 0) // left side
		{
			maze[0][index] = 'S';
			startingPoint.setX(index);
			startingPoint.setY(1);
		}
		else if (genSide == 1) // right side
		{
			maze[mazeSize - 1][index] = 'S';
			startingPoint.setX(index);
			startingPoint.setY(mazeSize - 2);
		}
		else if (genSide == 2) // upper side
		{
			maze[index][0] = 'S';
			startingPoint.setX(1);
			startingPoint.setY(index);
		}
		else if (genSide == 3) // lower side
		{
			maze[index][mazeSize -1] = 'S';
			startingPoint.setX(mazeSize - 2);
			startingPoint.setY(index);
		}

		maze[startingPoint.getY()][startingPoint.getX()] = ' ';

		return startingPoint;
	}

	public void printSmallM()
	{
		for(int i = 0; i < mazeDone.length; i++)
		{
			for(int j = 0; j < mazeDone.length; j++)
			{
				if(mazeDone[i][j])
					System.out.print("T ");
				else
					System.out.print("F ");
			}
			System.out.println();
		}
	}
	private Boolean isMovementPossible(int direction)
	{
		// TODO: Maybe replace with a switch statement

		if (direction == 0) // left
		{
			if (currentPointBool.getX() <= 0)
				return false;

			return !mazeDone[currentPointBool.getY()][currentPointBool.getX() - 1];
		}

		else if (direction == 1) // right
		{
			if (currentPointBool.getX() >= mazeDone.length - 1)
				return false;
			
			return !mazeDone[currentPointBool.getY()][currentPointBool.getX() + 1];
		}

		else if (direction == 2) // up
		{
			if (currentPointBool.getY() <= 0)
				return false;
			
			return !mazeDone[currentPointBool.getY() - 1][currentPointBool.getX()];
		}

		else // down
		{
			if (currentPointBool.getY() >= mazeDone.length - 1)
				return false;

			return !mazeDone[currentPointBool.getY() + 1][currentPointBool.getX()];
		}
	}
}
