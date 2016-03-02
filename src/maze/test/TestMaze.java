package maze.test;
import static org.junit.Assert.*;
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
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHeroLife());
		assertEquals(false, maze.getDragonLife());
	}

	// f)
	@Test
	public void testWinGame() 
	{
		Maze maze = new Maze(m1);
		assertEquals(new Point(3, 1), maze.getHeroPosition());
		maze.moveHero(-2,0);
		maze.moveHero(0, 2);
		assertEquals(new Point(1, 3), maze.getHeroPosition());
		assertEquals('A', maze.getHeroChar());
		maze.moveHero(1, 0);
		assertEquals(true, maze.getHeroLife());
		assertEquals(false, maze.getDragonLife());
		maze.moveHero(1, -2);
		maze.moveHero(1, 0);
		assertEquals(true, maze.getFinished());
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
