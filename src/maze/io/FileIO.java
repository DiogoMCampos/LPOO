package maze.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class FileIO
{

	private int size = 0;
	private int file;
	private String name;
	private char[][] maze;

	public FileIO(int file) throws IOException
	{
		FileReader fr;
		BufferedReader mazeReader;

		this.file = file;
		this.name = "savedMaze" + file + ".txt";
		
		fr = new FileReader(name);
		mazeReader = new BufferedReader(fr);

		String line;

		while((line = mazeReader.readLine()) != null)
			size++;
		

		maze = new char[size][size];
		mazeReader.close();

		
	}

	public char[][] getMaze()
	{
		return maze;
	}
	
	public void readMaze() throws IOException, InvalidMaze
	{
		FileReader fr;
		BufferedReader mazeReader;

		fr = new FileReader(name);
		mazeReader = new BufferedReader(fr);


		for(int i = 0; i < size; i++)
		{
			String line = mazeReader.readLine();
			for(int j = 0; j < size; j++)
			{
				maze[i][j] = line.charAt(j);
			}
		}
		if(size < 5)
			throw new InvalidMaze();
		mazeReader.close();
	}

	public void writeMaze(char[][] maze, int fileW) throws IOException
	{
		String nameW = "savedMaze" + fileW + ".txt";
		FileWriter fw = new FileWriter(nameW, false);
		PrintWriter mazeWrite = new PrintWriter(fw);

		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze.length; j++)
			{
				mazeWrite.print(maze[i][j]);
			}
			if(i != maze.length - 1)
				mazeWrite.println();
		}

		mazeWrite.close();

	}
}