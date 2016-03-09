package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestMazeDragonSleep 
{
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testSleepDragon() 
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.setDragonSleep(true);
		assertEquals('d', maze.getDragonChar());
		maze.moveHero(0, 1);
		assertEquals(new Point(3,2), maze.getHeroPosition());
		assertEquals(true, maze.getHeroLife());
		maze.setDragonSleep(false);
		assertEquals('D', maze.getDragonChar());
	}
	
	@Test
	public void testKillSleepDragon() 
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.setDragonSleep(true);
		assertEquals('d', maze.getDragonChar());
		maze.moveHero(-2, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(1, 0);
		assertEquals(false, maze.getDragonLife());
	}
	
	
	@Test(timeout = 1000)
	public void testRandomSleepDragon()
	{
		boolean sleep = false, noSleep = false;
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 3), maze.getDragonPosition());
		assertEquals('D', maze.getDragonChar());
		while(!sleep || !noSleep)
		{
			if(maze.sleepDragon() && !sleep)
			{
				sleep = true;
				assertEquals('d', maze.getDragonChar());
			}
			else if(!noSleep)
			{
				noSleep = true;
				assertEquals('D', maze.getDragonChar());
			}
		}
	}
}
