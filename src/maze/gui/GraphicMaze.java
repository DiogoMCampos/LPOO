package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import maze.logic.Maze;
import maze.gui.MazePanel;


public class GraphicMaze {

	private JFrame MazeFrame;
	private int size;
	private MazePanel panel;
	private Maze maze;
	private int x, y;
	private boolean buildMode;
	private int windowX, windowY;


	/**
	 * Create the application.
	 */
	public GraphicMaze(Maze maze, int x, boolean buildMode) {
		this.size = maze.getMaze().length + 1;
		this.maze = maze;
		this.x = x;
		this.buildMode = buildMode;
		this.windowX = size * 50 - 32;
		this.windowY = size * 50 - 3;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MazeFrame = new JFrame();
		MazeFrame.setTitle("Graphic Maze");
		
		if (buildMode) {
			displayInstructions();
			MazeFrame.setBounds(100, 100, windowX + 200, windowY);
			MazeFrame.setPreferredSize(new Dimension(windowX + 200, windowY));
			MazeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else {
			MazeFrame.setBounds(100, 100, windowX, windowY);
			MazeFrame.setPreferredSize(new Dimension(windowX, windowY));
			MazeFrame.setLocation(x, y);
		}
		
		x += 565;
		y = MazeFrame.getY();
		
		MazeFrame.setLocation(x, y);
		panel = new MazePanel(maze.getMaze(), maze.dragonsAlive());
		panel.setLayout(null);
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

	public void closeWindow()
	{
		MazeFrame.dispose();
	}
	
	public JFrame getFrame()
	{
		return MazeFrame;
	}
	
	public MazePanel getPanel()
	{
		return panel;
	}
	
	public void displayInstructions()
	{
		JOptionPane.showMessageDialog(MazeFrame,
				"The maze was generated randomly, but you can modify it."
				+ "\nIn order to do so, you can click on the Hero, Sword or Dragon(s) "
				+ "and click on the new position."
				+ "\nIf you'd like to modify the walls or ground, use the buttons accordingly.", 
				"Maze Builder Instructions",
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public void startPlayMode()
	{
		this.buildMode = false;
		MazeFrame.setBounds(100, 100, windowX, windowY);
		MazeFrame.setPreferredSize(new Dimension(windowX, windowY));
		MazeFrame.setLocation(x, y);
		
	}

	public int getSize() {
		return size;
	}
	
	public boolean getBuildMode() {
		return buildMode;
	}
	
	public int getWindowX() {
		return windowX;
	}
}
