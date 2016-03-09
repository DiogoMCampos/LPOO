package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import java.util.ArrayList;

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
		ArrayList<Dragon> dragons = maze.getDragons();		
		
		// If you add another dragon replace the index on "get" by it's index (the array is read from top to bottom and left to right)
		Dragon currentDragon = dragons.get(0);
		assertEquals(new Point(3, 3), currentDragon.getPosition());
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		currentDragon.setSleep(true);
		assertEquals('d', currentDragon.getChar());
		maze.moveHero(0, 1);
		assertEquals(new Point(3,2), maze.getHeroPosition());
		assertEquals(true, maze.getHeroLife());
		currentDragon.setSleep(false);
		assertEquals('D', currentDragon.getChar());
		// End copying here
	
}

@Test
public void testKillSleepDragon() 
{
	Maze maze = new Maze(m1);
	ArrayList<Dragon> dragons = maze.getDragons();

	// If you add another dragon replace the index on "get" by it's index (the array is read from top to bottom and left to right)
	Dragon currentDragon = dragons.get(0);
	assertEquals(new Point(3, 3), currentDragon.getPosition());
	assertEquals(new Point(3, 1), maze.getHeroPosition());
	currentDragon.setSleep(true);
	assertEquals('d', currentDragon.getChar());
	maze.moveHero(-2, 2);
	assertEquals(new Point(1, 3), maze.getHeroPosition());
	assertEquals('A', maze.getHeroChar());
	maze.moveHero(1, 0);
	assertEquals(false, currentDragon.getLife());
	// End copying here
}


@Test(timeout = 1000)
public void testRandomSleepDragon()
{
	Maze maze = new Maze(m1);
	ArrayList<Dragon> dragons = maze.getDragons();

	// If you add another dragon replace the index on "get" by it's index (the array is read from top to bottom and left to right)
	Dragon currentDragon = dragons.get(0);
	boolean sleep = false, noSleep = false;
	assertEquals(new Point(3, 3), currentDragon.getPosition());
	assertEquals('D', currentDragon.getChar());
	while(!sleep || !noSleep)
	{
		if(maze.sleepDragon(currentDragon) && !sleep)
		{
			sleep = true;
			assertEquals('d', currentDragon.getChar());
		}
		else if(!noSleep)
		{
			noSleep = true;
			assertEquals('D', currentDragon.getChar());
		}
	}
	// Stop copying here

}
}
