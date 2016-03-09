package maze.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import maze.logic.*;

public class TestMazeRandomMovement 
{
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testDragonMovement() 
	{
		Maze maze = new Maze(m1);
		ArrayList<Dragon> dragons = maze.getDragons();
		for (int i = 0; i < dragons.size(); i++)
		{
			Dragon currentDragon = dragons.get(i);
			assertEquals(new Point(3, 3), currentDragon.getPosition());
			maze.moveDragon(currentDragon);
			assertNotEquals(new Point(3,3), currentDragon.getPosition());
		}
	}
	
	@Test(timeout = 1000)
	public void testDecideMove()
	{
		Maze maze = new Maze(m1);
		ArrayList<Dragon> dragons = maze.getDragons();
		for (int i = 0; i < dragons.size(); i++)
		{
			Dragon currentDragon = dragons.get(i);
			boolean move = false, noMove = false;
			assertEquals(new Point(3, 3), currentDragon.getPosition());
			while(!move || !noMove)
			{
				if(maze.decideMove() && !move)
				{
					move = true;
					maze.moveDragon(currentDragon);
					assertNotEquals(new Point(3,3), currentDragon.getPosition());	
				}
				else if (!noMove)
				{
					noMove = true;
					Point p = currentDragon.getPosition();
					assertEquals(p, currentDragon.getPosition());
				}
			}
		}
	}

}
