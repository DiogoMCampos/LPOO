package maze.test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import maze.logic.*;


public class TestMaze
{
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	// a)
	@Test
	public void testMoveHeroToFreeCell() 
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-1,0);
		assertEquals(new Point(2, 1), maze.getHeroPosition());
	}

	// b)
	@Test
	public void testMoveHeroToWall()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(0,-1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
	}

	// c)
	@Test
	public void testCatchSword()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
	}

	// d)
	@Test
	public void testHeroDies() 
	{
		Maze maze = new Maze(m1);
		assertEquals('H', maze.getHeroChar());
		maze.moveHero(0,1);
		assertEquals(false, maze.getHeroLife());
	}

	// e)
	@Test
	public void testHeroKillsDragon() 
	{
		Maze maze = new Maze(m1);
		ArrayList<Dragon> dragons = maze.getDragons();
		// If you add another dragon replace the index on "get" by it's index (the array is read from top to bottom and left to right)

		Dragon currentDragon = dragons.get(0);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHeroLife());
		assertEquals(false, currentDragon.getLife());
		// End copying here
	}

	// f)
	@Test
	public void testWinGame() 
	{
		Maze maze = new Maze(m1);
		ArrayList<Dragon> dragons = maze.getDragons();

		Dragon currentDragon = dragons.get(0);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHeroLife());
		assertEquals(false, currentDragon.getLife());
		maze.moveHero(1, -2);
		maze.moveHero(1, 0);
		assertEquals(true, maze.getFinished());
		// End copying here
	}

	// g)
	@Test
	public void testMoveHeroToExitNoSword()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(1,0);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		assertEquals(false, maze.getFinished());
	}

	// h)
	@Test
	public void testMoveHeroToExitWithSword()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(3, -2);
		assertEquals(false, maze.getFinished());
	}


}
