package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MazePanel extends JPanel {
	private BufferedImage wall;
	private BufferedImage floor;
	private BufferedImage unarmedHero;
	private BufferedImage armedHero;
	private BufferedImage dragon;
	private BufferedImage sword;
	private BufferedImage openDoor;
	private BufferedImage closedDoor;
	private BufferedImage sleepyDragon;
	private BufferedImage armedDragon;
	private int x=0, y=0, width=50, height=50;
	private int numDragonsAlive;
	private char[][] maze;

	
	/**
	 * Loads the graphic maze. The images used were taken from:
	 * http://opengameart.org/content/lots-of-free-2d-tiles-and-sprites-by-hyptosis
	 * and the door was taken from:
	 * http://opengameart.org/content/cute-dungeon-lpc-edit
	 * Some images were edited such as the door, the hero or the dragon.
	 * A big thanks to the authors: Hyptosis and Zabin (1st link), Buch, Sharm and William.Thompsonj
	 * @param maze the matrix to display
	 * @param numDragonsAlive number of dragons alive
	 */
	public MazePanel(char[][] maze, int numDragonsAlive) {
		try {
			wall =  ImageIO.read(new File("wall.png"));
			floor = ImageIO.read(new File("floor1.png"));
			unarmedHero = ImageIO.read(new File("unhero.png"));
			armedHero = ImageIO.read(new File("arhero.png"));
			dragon = ImageIO.read(new File("dragon.png"));
			sword = ImageIO.read(new File("sword.png"));
			openDoor = ImageIO.read(new File("opendoor.png"));
			closedDoor = ImageIO.read(new File("closeddoor.png"));
			sleepyDragon = ImageIO.read(new File("sleepyDragon.png"));
			armedDragon = ImageIO.read(new File("armedDragon.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		this.maze = maze;
		this.numDragonsAlive = numDragonsAlive;

	}

	public void setDragonsAlive(int numDragonsAlive)
	{
		this.numDragonsAlive = numDragonsAlive;
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		x = 0;
		y = 0;
		super.paintComponent(g);
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				if(maze[i][j] == 'X')
					g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(), wall.getHeight(), null);
				else if(maze[i][j] == ' ')
					g.drawImage(floor, x, y, x + width, y + height, 0, 0, floor.getWidth(), floor.getHeight(), null);
				else if(maze[i][j] == 'H')
					g.drawImage(unarmedHero, x, y, x + width, y + height, 0, 0, unarmedHero.getWidth(), unarmedHero.getHeight(), null);
				else if(maze[i][j] == 'A')
					g.drawImage(armedHero, x, y, x + width, y + height, 0, 0, armedHero.getWidth(), armedHero.getHeight(), null);
				else if(maze[i][j] == 'D')
					g.drawImage(dragon, x, y, x + width, y + height, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
				else if(maze[i][j] == 'd')
					g.drawImage(sleepyDragon, x, y, x + width, y + height, 0, 0, sleepyDragon.getWidth(), sleepyDragon.getHeight(), null);
				else if(maze[i][j] == 'F')
					g.drawImage(armedDragon, x, y, x + width, y + height, 0, 0, armedDragon.getWidth(), armedDragon.getHeight(), null);
				else if(maze[i][j] == 'E')
					g.drawImage(sword, x, y, x + width, y + height, 0, 0, sword.getWidth(), sword.getHeight(), null);
				else if(maze[i][j] == 'S' && numDragonsAlive != 0)
					g.drawImage(closedDoor, x, y, x + width, y + height, 0, 0, closedDoor.getWidth(), closedDoor.getHeight(), null);
				else if(maze[i][j] == 'S' && numDragonsAlive == 0)
					g.drawImage(openDoor, x, y, x + width, y + height, 0, 0, openDoor.getWidth(), openDoor.getHeight(), null);
				x += width;
			}
			y += height;
			x = 0;
		}
	}

}
