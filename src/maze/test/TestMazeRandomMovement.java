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
	public void testMoveHeroToFreeCell() 
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		maze.moveDragon();
		assertNotEquals(new Point(3,3), maze.getDragonPosition());
	}

}
