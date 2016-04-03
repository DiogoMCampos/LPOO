package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.logic.Maze;
import maze.logic.MazeBuilder;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnUp;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnDown;
	private JLabel status;
	private JComboBox<String> comboBox;
	private JRootPane rootPane;
	private KeyStroke upKeyStroke;
	private KeyStroke downKeyStroke;
	private KeyStroke rightKeyStroke;
	private KeyStroke leftKeyStroke;
	private Maze maze;
	private GraphicInterface gi = new GraphicInterface();
	private int mazeSize = 11;
	private int numberDragons = 1;
	private JCheckBox graphicMazeSel;
	private JCheckBox randomDragons;
	private GraphicMaze GM;
	private boolean graphicMode;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Maze Game");
		frame.setBounds(100, 100, 561, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initializeRootPane();
		
		JLabel lblMazeSize = new JLabel("Maze Size");
		lblMazeSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMazeSize.setBounds(54, 35, 67, 26);
		frame.getContentPane().add(lblMazeSize);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setText("11");
		textField.setBounds(191, 38, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfDragons.setBounds(54, 83, 127, 26);
		frame.getContentPane().add(lblNumberOfDragons);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setText("1");
		textField_1.setBounds(191, 86, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		randomDragons = new JCheckBox("Random");
		randomDragons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		randomDragons.setBounds(290, 87, 97, 23);
		randomDragons.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent changeEvent) 
			{
				if(randomDragons.isSelected())
					textField_1.setEnabled(false);
				else
					textField_1.setEnabled(true);
			}
		});
		frame.getContentPane().add(randomDragons);

		JLabel lblNewLabel = new JLabel("Dragon Mode ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 131, 97, 26);
		frame.getContentPane().add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Select the desired dragon behaviour");
		comboBox.setMaximumRowCount(3);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(172, 133, 189, 22);
		comboBox.addItem("No movement");
		comboBox.addItem("Random movement");
		comboBox.addItem("Random movement and sleep");
		frame.getContentPane().add(comboBox);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(54, 168, 260, 260);
		frame.getContentPane().add(textArea);

		

		JButton btnNewButton = new JButton("Exit Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(397, 85, 107, 23);
		frame.getContentPane().add(btnNewButton);


		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.updateGame(0);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
				int dragonsAlive = maze.dragonsAlive();

				if(graphicMode)
					GM.updatePanel();
				if(maze.getFinished())
				{
					closeInputs();
					if(dragonsAlive == 0)
						status.setText("You won the game");
					else
						status.setText("You were killed!");
				}
				else
				{
					if(dragonsAlive == 1)
						status.setText("Move the hero. " +  dragonsAlive + " dragon to go.");
					else if(dragonsAlive == 0)
						status.setText("Move to the exit. ");
					else
						status.setText("Move the hero. " +  dragonsAlive + " dragons to go.");
				}
			}
		});
		btnUp.setBounds(395, 254, 70, 23);
		frame.getContentPane().add(btnUp);

		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				maze.updateGame(1);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
				int dragonsAlive = maze.dragonsAlive();

				if(graphicMode)
					GM.updatePanel();
				if(maze.getFinished())
				{
					closeInputs();
					if(dragonsAlive == 0)
						status.setText("You won the game");
					else
						status.setText("You were killed!");
				}
				else
				{

					if(dragonsAlive == 1)
						status.setText("Move the hero. " +  dragonsAlive + " dragon to go.");
					else if(dragonsAlive == 0)
						status.setText("Move to the exit. ");
					else
						status.setText("Move the hero. " +  dragonsAlive + " dragons to go.");
				}
			}
		});
		btnLeft.setBounds(358, 288, 70, 23);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.updateGame(3);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
				int dragonsAlive = maze.dragonsAlive();
				if(graphicMode)
					GM.updatePanel();
				if(maze.getFinished())
				{
					closeInputs();
					if(dragonsAlive == 0)
						status.setText("You won the game");
					else
						status.setText("You were killed!");
				}
				else
				{

					if(dragonsAlive == 1)
						status.setText("Move the hero. " +  dragonsAlive + " dragon to go.");
					else if(dragonsAlive == 0)
						status.setText("Move to the exit. ");
					else
						status.setText("Move the hero. " +  dragonsAlive + " dragons to go.");
				}
			}
		});
		btnRight.setBounds(439, 288, 70, 23);
		frame.getContentPane().add(btnRight);

		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.updateGame(2);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
				int dragonsAlive = maze.dragonsAlive();
				if(graphicMode)
					GM.updatePanel();
				if(maze.getFinished())
				{
					closeInputs();
					if(dragonsAlive == 0)
						status.setText("You won the game");
					else
						status.setText("You were killed!");
				}
				else
				{

					if(dragonsAlive == 1)
						status.setText("Move the hero. " +  dragonsAlive + " dragon to go.");
					else if(dragonsAlive == 0)
						status.setText("Move to the exit. ");
					else
						status.setText("Move the hero. " +  dragonsAlive + " dragons to go.");
				}
			}
		});
		btnDown.setBounds(395, 322, 70, 23);
		frame.getContentPane().add(btnDown);

		

		status = new JLabel("Create a new maze to play.");
		status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		status.setBounds(54, 432, 260, 25);
		frame.getContentPane().add(status);

		graphicMazeSel = new JCheckBox("Graphic Maze");
		graphicMazeSel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		graphicMazeSel.setBounds(397, 135, 107, 23);
		frame.getContentPane().add(graphicMazeSel);
		
		JButton btnNewMaze = new JButton("New Maze");
		btnNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String inputSize = textField.getText();
				mazeSize = Integer.parseInt(inputSize);
				graphicMode = graphicMazeSel.isSelected();
				
				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				int maxHeight = dimension.height / 50 - 2;
				System.out.println(maxHeight);
				if(mazeSize <= 5) // dimension must be higher than five
				{
					mazeSize = 7;
					textField.setText("7");
				}
				else if(mazeSize % 2 == 0) // dimension must be odd
				{
					mazeSize++;
					Integer mS = mazeSize;
					textField.setText(mS.toString());
				}

				String inputDragons = textField_1.getText();
				numberDragons = Integer.parseInt(inputDragons);
				if(numberDragons < 0 || randomDragons.isSelected())
				{
					numberDragons = 0;	
				}

				
				textArea.setEditable(true);
				btnUp.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnDown.setEnabled(true);

				MazeBuilder mb = new MazeBuilder(mazeSize, numberDragons);
				char [][] matrix = mb.getMaze();
				maze = new Maze(matrix);

				
				
				if(graphicMode)
				{
					if(mazeSize > maxHeight)
					{
						mazeSize = maxHeight;
						Integer mS = mazeSize;
						textField.setText(mS.toString());
						mb = new MazeBuilder(mazeSize, numberDragons);
						matrix = mb.getMaze();
						maze = new Maze(matrix);
					}
					GM = new GraphicMaze(maze, frame.getLocationOnScreen().x);
				}
				
				status.setText("Move the hero to pick the sword.");

				int mode = comboBox.getSelectedIndex();
				maze.setMode(mode);
				System.out.println(mode);

				String printable = gi.print(matrix);
				textArea.setText(printable);
			}
		});
		btnNewMaze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewMaze.setBounds(397, 38, 107, 23);
		frame.getContentPane().add(btnNewMaze);
		
	}

	public void closeInputs()
	{
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
	}
	
	public void initializeRootPane()
	{
		rootPane = frame.getRootPane();
		
		Action upKeyAction = new AbstractAction("upKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnUp.doClick();
			} 
		};

		Action downKeyAction = new AbstractAction("downKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnDown.doClick();
			}
		};

		Action rightKeyAction = new AbstractAction("rightKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRight.doClick();
			} 
		};

		Action leftKeyAction = new AbstractAction("leftKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnLeft.doClick();
			}
		};

		upKeyStroke = KeyStroke.getKeyStroke("UP");
		downKeyStroke = KeyStroke.getKeyStroke("DOWN");
		rightKeyStroke = KeyStroke.getKeyStroke("RIGHT");
		leftKeyStroke = KeyStroke.getKeyStroke("LEFT");
		
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStroke, "upKeyAction");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStroke, "downKeyAction");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStroke, "rightKeyAction");
		rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStroke, "leftKeyAction");
		rootPane.getActionMap().put("upKeyAction", upKeyAction);
		rootPane.getActionMap().put("downKeyAction", downKeyAction);
		rootPane.getActionMap().put("rightKeyAction", rightKeyAction);
		rootPane.getActionMap().put("leftKeyAction", leftKeyAction);
	}

}
