package maze.logic;

import java.util.Random;

public class MazeBuilder {

  char maze[][];
  int mazeSize;

  // restriction: size has to be an odd number
  public MazeBuilder(int size)
  {
    this.mazeSize = size;
    maze = new char[size][size];

    if (size % 2 == 0)
    {
      size++;
    }

    for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				maze[i][j] = 'X';

    Random rand = new Random();
    int genSide = rand.nextInt(4);
    int genLimit = (size - 1) / 2;
    int index = ((rand.nextInt(genLimit) + 1) * 2) - 1;
    int startingX, startingY;

    if (genSide == 0) // left side
    {
      maze[0][index] = 'S';
      startingX = index;
      startingY = 1;
    }
    else if (genSide == 1) // right side
    {
      maze[size - 1][index] = 'S';
      startingX = index;
      startingY = size - 1;
    }
    else if (genSide == 2) // upper side
    {
      maze[index][0] = 'S';
      startingX = 0;
      startingY = index;
    }
    else if (genSide == 3) // lower side
    {
      maze[index][size -1] = 'S';
      startingX = size - 1;
      startingY = index;
    }

    maze[startingY][startingX] = ' ';

  }
}
