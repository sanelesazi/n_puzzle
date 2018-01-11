import java.io.*;

public class heuristic_val extends program
{
    private int row = 0;
    private int col = 0;
    IO io_class = new IO();

    public class Direction
    {
        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;
    }
    private int get_blankpiece(int [][]puzzle)
    {
        for (int i = 0; i < io_class.puzzle_size; i++)
        {
            for (int j = 0;j < io_class.puzzle_size; j++)
            {
                if (puzzle[i][j] == 0)
                {
                    row = i;
                    col = j;
                    return (1);
                }
            }
        }
        return (0);
    }

    public int get_heuristicval(int [][]puzzle)
    {
        int x = 0; int y = 0; int heuristicval = 0;

        for (int i = 0; i < io_class.puzzle_size; i++)
        {
            for (int j = 0; j < io_class.puzzle_size; j++)
            {
                if (puzzle[i][j] != 0)
                {
                    y = (puzzle[i][j] - 1) % io_class.puzzle_size;
                    x = (puzzle[i][j] - 1) / io_class.puzzle_size;
                    heuristicval += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return (heuristicval);
    }

    private void print_puzzle(int [][]pz)
    {
        for (int i = 0; i < pz.length; i++)
        {
            for (int j = 0; j < pz.length; j++)
                System.out.print(pz[i][j]+" ");
            System.out.println("");
        }
    }

    private int[][] getcopy_puzzle(int [][]init_puzzle)
    {
        int [][]copy = new int [io_class.puzzle_size][io_class.puzzle_size];
        for (int x = 0; x < io_class.puzzle_size; x++)
        {
            for (int y = 0; y < io_class.puzzle_size; y++)
            {
                copy[x][y] = init_puzzle[x][y];
            }
        }
        return (copy);
    }

    public int[][] move(String dir, int [][]puzzle)
    {
        int [][]temp = getcopy_puzzle(puzzle);
        if (dir == "left")
        {
            temp[row][col] = puzzle[row][col - 1];
            temp[row][col - 1] = 0;
            print_puzzle(temp);
            return (temp);
        }
        else if (dir == "right")
        {
            temp[row][col] = puzzle[row][col + 1];
            temp[row][col + 1] = 0;
            print_puzzle(temp);
            return (temp);
        }
        else if (dir == "up")
        {
            temp[row][col] = puzzle[row - 1][col];
            temp[row - 1][col] = 0;
            print_puzzle(temp);
            return (temp);
        }
        else if (dir == "down")
        {
            temp[row][col] = puzzle[row + 1][col];
            temp[row + 1][col] = 0;
            print_puzzle(temp);
            return (temp);
        }
        else
            return (puzzle);
    }

    public int get_move(int [][]puzzle, String dir)
    {
        get_blankpiece(puzzle);
        if (col - 1 >= 0 && dir == "left")
            return (1);
        else if (col + 1 < io_class.puzzle_size && dir == "right")
            return (1);
        else if (row - 1 >= 0 && dir == "up")
            return (1);
        else if (row + 1 < io_class.puzzle_size && dir == "down")
            return (1);
        else
            return (0);
    }
}