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
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(-1,0);
		assertEquals(new Point(2, 1), maze.getHero().getPosition());
	}

	// b)
	@Test
	public void testMoveHeroToWall()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(0,-1);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
	}

	// c)
	@Test
	public void testCatchSword()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHero().getPosition());
		assertEquals('A', maze.getHero().getChar());
	}

	// d)
	@Test
	public void testHeroDies() 
	{
		Maze maze = new Maze(m1);
		assertEquals('H', maze.getHero().getChar());
		maze.moveHero(0,1);
		assertEquals(false, maze.getHero().getLife());
	}

	// e)
	@Test
	public void testHeroKillsDragon() 
	{
		Maze maze = new Maze(m1);
		ArrayList<Dragon> dragons = maze.getDragons();
		// If you add another dragon replace the index on "get" by it's index (the array is read from top to bottom and left to right)

		Dragon currentDragon = dragons.get(0);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHero().getPosition());
		assertEquals('A', maze.getHero().getChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHero().getLife());
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
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHero().getPosition());
		assertEquals('A', maze.getHero().getChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHero().getLife());
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
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(1,0);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		assertEquals(false, maze.getFinished());
	}

	// h)
	@Test
	public void testMoveHeroToExitWithSword()
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHero().getPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHero().getPosition());
		assertEquals('A', maze.getHero().getChar());
		maze.moveHero(3, -2);
		assertEquals(false, maze.getFinished());
	}


}
