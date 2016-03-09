package maze.test;

import static org.junit.Assert.*;
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
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		maze.moveDragon();
		assertNotEquals(new Point(3,3), maze.getDragonPosition());
	}
	
	@Test(timeout = 1000)
	public void testDecideMove()
	{
		boolean move = false, noMove = false;
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		while(!move || !noMove)
		{
			if(maze.decideMove() && !move)
			{
				move = true;
				maze.moveDragon();
				assertNotEquals(new Point(3,3), maze.getDragonPosition());	
			}
			else if (!noMove)
			{
				noMove = true;
				Point p = maze.getDragonPosition();
				assertEquals(p, maze.getDragonPosition());
			}
		}
	}

}
