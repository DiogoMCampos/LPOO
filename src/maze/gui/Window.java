package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.logic.Maze;
import maze.logic.MazeBuilder;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private Maze maze;
	private GraphicInterface gi = new GraphicInterface();
	private int mazeSize = 11;
	private int numberDragons = 1;

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

		JCheckBox checkBox = new JCheckBox("Random");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBounds(290, 87, 97, 23);
		checkBox.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent changeEvent) 
			{
				if(checkBox.isSelected())
					textField_1.setEnabled(false);
				else
					textField_1.setEnabled(true);
			}
		});
		frame.getContentPane().add(checkBox);

		JLabel lblNewLabel = new JLabel("Dragon Mode ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 131, 97, 26);
		frame.getContentPane().add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
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


		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.moveHero(0, -1);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
			}
		});
		btnUp.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_UP)
					btnUp.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		btnUp.setBounds(395, 254, 70, 23);
		frame.getContentPane().add(btnUp);

		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				maze.moveHero(-1, 0);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
			}
		});
		btnLeft.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
					btnLeft.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		btnLeft.setBounds(358, 288, 70, 23);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.moveHero(1, 0);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
			}
		});
		btnRight.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
					btnRight.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		btnRight.setBounds(439, 288, 70, 23);
		frame.getContentPane().add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				maze.moveHero(0, 1);
				String printable = gi.print(maze.getMaze());
				textArea.setText(printable);
			}
		});
		btnDown.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
					btnDown.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		btnDown.setBounds(395, 322, 70, 23);
		frame.getContentPane().add(btnDown);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(54, 432, 260, 25);
		frame.getContentPane().add(lblNewLabel_1);


		JButton btnNewMaze = new JButton("New Maze");
		btnNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String inputSize = textField.getText();
				mazeSize = Integer.parseInt(inputSize);
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
				if(numberDragons < 0 || checkBox.isSelected())
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

				String printable = gi.print(matrix);
				textArea.setText(printable);
			}
		});
		btnNewMaze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewMaze.setBounds(397, 38, 107, 23);
		frame.getContentPane().add(btnNewMaze);

	}
}
