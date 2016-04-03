package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import maze.logic.Maze;
import maze.gui.MazePanel;


public class GraphicMaze {

	private JFrame MazeFrame;
	private int size;
	private MazePanel panel;
	private Maze maze;
	private int x, y;


	/**
	 * Create the application.
	 */
	public GraphicMaze(Maze maze, int x) {
		this.size = maze.getMaze().length + 1;
		this.maze = maze;
		this.x = x;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MazeFrame = new JFrame();
		MazeFrame.setTitle("Graphic Maze");
		MazeFrame.setBounds(100, 100, size * 50, size * 50);
		MazeFrame.setPreferredSize(new Dimension(size * 50, size * 50));
		MazeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		x += 565;
		y = MazeFrame.getY();
		
		MazeFrame.setLocation(x, y);
		panel = new MazePanel(maze.getMaze(), maze.dragonsAlive());
		MazeFrame.getContentPane().add(panel);

		MazeFrame.pack();
		
		MazeFrame.setVisible(true);
		
		//panel.requestFocus();
	}
	
	public void updatePanel()
	{
		panel.setDragonsAlive(maze.dragonsAlive());
		MazeFrame.repaint();
	}

}