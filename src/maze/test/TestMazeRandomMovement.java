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
		int i = 0;
		while(i < 5)
		{
			maze.moveDragon();
			if(maze.getDragonPosition() != new Point(3,3))
				break;
			i++;
		}
		assertNotEquals(new Point(3,3), maze.getDragonPosition());
	}

}
