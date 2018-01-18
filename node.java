import java.util.*;
//import java.io.*;

class node extends program
{
    IO io_class = new IO();
    private int [][]goal_state = io_class.getGoalState();
    public int size = program.puzzle_size;
    public LinkedList <node> child_nodes = new LinkedList<node>();
    public node parent;
    public int [][]puzzle = new int[size][size];
    public int row = 0;
    public int col = 0;
    public int h_val = 0;
    
    public node(int [][]puz)
    {
        set_puzzle(puz);
    }

    public void set_puzzle(int [][]puz)
    {
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
                this.puzzle[x][y] = puz[x][y];
        }
    }

    public int [][]copy_puzzle(int [][]src)
    {
        int [][]dest = new int[size][size];
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
                dest[x][y] = src[x][y];
        }
        return (dest);
    }

    public boolean is_goal()
    {
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (puzzle[x][y] != goal_state[x][y])
                    return (false);
            }
        }
        return (true);
    }

    public boolean same_puzzle (int [][]puz)
    {
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (this.puzzle[x][y] != puz[x][y])
                    return (false);
            }
        }
        return (true);
    }

    public void find_piece(int [][]puz, int piece)
    {
        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (puz[x][y] == piece)
                {
                    row = x;
                    col = y;
                }
            }
        }
    }

    public int calc_hval(int [][]puz)
    {
        int f = 0;
        int [][]goal_state = io_class.getGoalState();

        for (int x = 0; x < size; x++)
        {
            for (int y = 0; y < size; y++)
            {
                find_piece(goal_state, puz[x][y]);
                f += Math.abs(x - row) + Math.abs(y - col);
            }
        }
        return (f);
    }

    public void move_left(int [][]pz)
    {
        if (col - 1 > -1)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row][col - 1];
            copy_puzzle[row][col - 1] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child_nodes.add(child);
        }
    }

    public void move_right(int [][]pz)
    {
        if (col + 1 < size)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row][col + 1];
            copy_puzzle[row][col + 1] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child_nodes.add(child);
        }
    }

    public void move_up(int [][]pz)
    {
        if (row - 1 > -1)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row - 1][col];
            copy_puzzle[row - 1][col] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child_nodes.add(child);
        }
    }

    public void move_down(int [][]pz)
    {
        if (row + 1 < size)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row + 1][col];
            copy_puzzle[row + 1][col] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child_nodes.add(child);
        }
    }

    public void possible_moves()
    {
        find_piece(this.puzzle, 0);
        move_left(this.puzzle);
        move_right(this.puzzle);
        move_up(this.puzzle);
        move_down(this.puzzle);
    }
}