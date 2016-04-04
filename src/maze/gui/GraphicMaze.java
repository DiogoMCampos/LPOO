package maze.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import maze.logic.Dragon;
import maze.logic.Maze;
import maze.logic.Point;
import maze.gui.MazePanel;


public class GraphicMaze {

	private JFrame MazeFrame;
	private int size;
	private MazePanel panel;
	private Maze maze;
	private int x, y;
	private boolean buildMode;
	private int windowX, windowY;

	private char charSelected = 'N';
	private Point positionSelected;



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

		if (buildMode)
			addBuildOptions();

		//panel.requestFocus();
	}

	private void addBuildOptions() {
		int buttonX = (size - 1) * 50 + 40;
		int buttonY = ((size / 2) - 4) * 50;

		JButton addDragon = new JButton("Add Dragon");
		addDragon.setFocusable(false);
		addDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				charSelected = 'D';
				positionSelected = new Point(-1, -1);
			}
		});
		addDragon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(addDragon);
		addDragon.setBounds(buttonX, buttonY + 150, 120, 25);

		JButton btnPlay = new JButton("Play Game");
		btnPlay.setFocusable(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				startPlayMode();
			}
		});
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnPlay);
		btnPlay.setBounds(buttonX, buttonY + 200, 120, 25);


		panel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (buildMode) {
					int x = e.getX() / 50;
					int y = e.getY() / 50;
					if (x < size - 1) {
						if (charSelected == 'N') {
							charSelected = maze.getMaze()[y][x];
							if (charSelected == ' ')
								charSelected = 'N';
							else  
								positionSelected = new Point(x, y);
						}
						else {
							if (maze.getMaze()[y][x] == ' ') {		
								if (charSelected == 'H') {
									maze.getMaze()[positionSelected.getY()][positionSelected.getX()] = ' ';
									maze.getHero().setX(x);
									maze.getHero().setY(y);
								}
								else if (charSelected == 'D') {
									if (positionSelected.equals(new Point(-1, -1))) {
										Dragon newDragon = new Dragon(x, y);
										maze.getDragons().add(newDragon);
									}
									else {
										for (int i = 0; i < maze.getDragons().size(); i++) {
											Dragon currentDragon = maze.getDragons().get(i);
											if (currentDragon.getPosition().equals(positionSelected)) {
												int currentX = positionSelected.getX();
												int currentY = positionSelected.getY();
												currentDragon.setX(x);
												currentDragon.setY(y);
												maze.getMaze()[currentY][currentX] = ' ';
											}
										}
									}
									

								}
							}
							maze.getMaze()[y][x] = charSelected;
							charSelected = 'N';
							updatePanel();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		});
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
