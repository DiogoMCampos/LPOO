package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		textField.setBounds(133, 40, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfDragons.setBounds(54, 83, 127, 26);
		frame.getContentPane().add(lblNumberOfDragons);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setText("1");
		textField_1.setBounds(191, 88, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox checkBox = new JCheckBox("Random");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBounds(290, 87, 97, 23);
		frame.getContentPane().add(checkBox);
		
		JLabel lblNewLabel = new JLabel("Dragon Mode ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 131, 97, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Teste");
		comboBox.setMaximumRowCount(3);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(172, 136, 189, 22);
		comboBox.addItem("No movement");
		comboBox.addItem("Random movement");
		comboBox.addItem("Random movement and sleep");
		frame.getContentPane().add(comboBox);
		
		JButton btnNewMaze = new JButton("New Maze");
		btnNewMaze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewMaze.setBounds(397, 38, 107, 23);
		frame.getContentPane().add(btnNewMaze);
		
		JButton btnNewButton = new JButton("Exit Game");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(397, 85, 107, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(54, 190, 307, 257);
		frame.getContentPane().add(textArea);
		
		JButton btnUp = new JButton("Up");
		btnUp.setBounds(413, 254, 60, 23);
		frame.getContentPane().add(btnUp);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(376, 288, 60, 23);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(457, 288, 60, 23);
		frame.getContentPane().add(btnRight);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(413, 322, 60, 23);
		frame.getContentPane().add(btnDown);

	}
}
