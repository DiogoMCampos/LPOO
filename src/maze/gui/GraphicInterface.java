package maze.gui;

public class GraphicInterface 
{
	public String print(char[][] matrix)
	{
		String ret = "";
		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				if (j == matrix[i].length - 1)
				{
					ret += matrix[i][j] + "\n";
					System.out.println(matrix[i][j]);
				}
				else
				{
					ret += matrix[i][j] + " ";
					System.out.print(matrix[i][j] + " ");
				}
			}
		}

		return ret;
	}
}
