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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

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
	private JLabel status;

	private char charSelected = 'N';
	private Point positionSelected;
	private boolean addWallMode;
	private boolean deleteWallMode;
	private boolean deleteDragonMode;



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
		int buttonX = (size - 1) * 50 + 30;
		int buttonY = ((size / 2) - 4) * 50;
		
		status = new JLabel("Nothing selected", SwingConstants.CENTER);
		status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		status.setBounds(buttonX - 20, buttonY - 50, 180, 25);
		panel.add(status);
		
		JButton addWall = new JButton("Add Wall");
		addWall.setFocusable(false);
		addWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (charSelected == 'N') {
					addWallMode = true;
					charSelected = 'M';
					status.setText("Click to add a wall.");
				}
			}
		});
		addWall.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(addWall);
		addWall.setBounds(buttonX, buttonY, 140, 25);
		
		JButton deleteWall = new JButton("Delete Wall");
		deleteWall.setFocusable(false);
		deleteWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				if (charSelected == 'N') {
					deleteWallMode = true;
					charSelected = 'M';
					status.setText("Click to delete a wall.");
				}
			}
		});
		deleteWall.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(deleteWall);
		deleteWall.setBounds(buttonX, buttonY + 50, 140, 25);

		JButton addDragon = new JButton("Add Dragon");
		addDragon.setFocusable(false);
		addDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				charSelected = 'D';
				positionSelected = new Point(-1, -1);
				status.setText("Click to add a dragon.");
			}
		});
		addDragon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(addDragon);
		addDragon.setBounds(buttonX, buttonY + 100, 140, 25);
		
		JButton deleteDragon = new JButton("Delete Dragon");
		deleteDragon.setFocusable(false);
		deleteDragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				if (charSelected == 'N' && maze.getDragons().size() != 0) {
					deleteDragonMode = true;
					charSelected = 'M';
					status.setText("Click to delete a dragon.");
				}
				else
					displayError("There are no dragons remaining to remove.");
			}
		});
		deleteDragon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(deleteDragon);
		deleteDragon.setBounds(buttonX, buttonY + 150, 140, 25);

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
		btnPlay.setBounds(buttonX, buttonY + 200, 140, 25);


		panel.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (buildMode) {
					int mouseX = e.getX() / 50;
					int mouseY = e.getY() / 50;
					if (addWallMode && mouseX < size - 1) {
						if (maze.getMaze()[mouseY][mouseX] == ' ') {
							maze.getMaze()[mouseY][mouseX] = 'X';
							addWallMode = false;
							charSelected = 'N';
							status.setText("Nothing selected.");
						}
					}
					else if (deleteWallMode) {
						if (mouseX > 0 && mouseY > 0 && mouseX < size - 2 && mouseY < size - 2) {
							if (maze.getMaze()[mouseY][mouseX] == 'X')
								maze.getMaze()[mouseY][mouseX] = ' ';
							deleteWallMode = false;
							charSelected = 'N';
							status.setText("Nothing selected.");
						}
					}
					else if (deleteDragonMode && mouseX < size -1) {
						if (maze.getMaze()[mouseY][mouseX] == 'D') {
							maze.getMaze()[mouseY][mouseX] = ' ';
							Point dragonPosition = new Point(mouseX, mouseY);
							int dragonIndex;
							for (dragonIndex = 0; dragonIndex < maze.getDragons().size(); dragonIndex++)
								if (maze.getDragons().get(dragonIndex).getPosition().equals(dragonPosition))
									break;
							maze.getDragons().remove(dragonIndex);
							deleteDragonMode = false;
							charSelected = 'N';
							status.setText("Nothing selected.");
						}
					}
					else if (mouseX < size - 1) {
						// char 'N' is corresponds to no char selected
						if (charSelected == 'N') {
							charSelected = maze.getMaze()[mouseY][mouseX];
							if (charSelected == ' ' || charSelected == 'X')
								charSelected = 'N';
							else  
								positionSelected = new Point(mouseX, mouseY);
							if (charSelected == 'H')
								status.setText("Hero selected.");
							if (charSelected == 'D')
								status.setText("Dragon selected.");
							if (charSelected == 'E')
								status.setText("Sword selected.");
							if (charSelected == 'S')
								status.setText("Exit selected.");
						}
						else {
							if (charSelected == 'S') {
								if (mouseX == 0 && maze.getMaze()[mouseY][1] != 'X' || 
										mouseX == size - 2 && maze.getMaze()[mouseY][size - 3] != 'X' || 
										mouseY == 0 && maze.getMaze()[1][mouseX] != 'X' || 
										mouseY == size - 2 && maze.getMaze()[size - 3][mouseX] != 'X') {
									replaceCharacter(mouseX, mouseY, 'X');
									status.setText("Nothing selected.");
								} else {
									displayError("That's not a valid position for an exit.");
								}
							}
							else if (maze.getMaze()[mouseY][mouseX] == ' ') {
									replaceCharacter(mouseX, mouseY, ' ');
									status.setText("Nothing selected.");
							}
						}
					}
					
					updatePanel();
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
	
	public void displayError(String error) {
		JOptionPane.showMessageDialog(MazeFrame,
			    error,
			    "Building error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void displayWon() {
		JOptionPane.showMessageDialog(MazeFrame,
			    "You won the game");
		MazeFrame.dispose();
	}
	public void displayLost() {
		JOptionPane.showMessageDialog(MazeFrame,
			    "You lost the game",
			    "Message",
			    JOptionPane.ERROR_MESSAGE);
		MazeFrame.dispose();
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
	
	public void replaceCharacter(int mouseX, int mouseY, char replacement) {
		int oldX = positionSelected.getX();
		int	oldY = positionSelected.getY();
		if (oldX != -1) // because when you add a new char, you don't have to delete anything
			maze.getMaze()[oldY][oldX] = replacement;
		if (charSelected == 'H') {
			maze.getHero().setX(mouseX);
			maze.getHero().setY(mouseY);
		}
		else if (charSelected == 'D') {
			if (positionSelected.equals(new Point(-1, -1))) {
				Dragon newDragon = new Dragon(mouseX, mouseY);
				maze.getDragons().add(newDragon);
			}
			else {
				for (int i = 0; i < maze.getDragons().size(); i++) {
					Dragon currentDragon = maze.getDragons().get(i);
					if (currentDragon.getPosition().equals(positionSelected)) {											
						currentDragon.setX(mouseX);
						currentDragon.setY(mouseY);
						break;
					}
				}
			}
		}
		
		maze.getMaze()[mouseY][mouseX] = charSelected;
		charSelected = 'N';
	}
}
