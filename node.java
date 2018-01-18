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
    public int move_cost = 0;
    public String direction = "initial";
    
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

    public int cal_move_cost(int [][]puz)
    {
        int [][]goal_state = io_class.getGoalState();
        int f = 0;

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

    public void printpuzzle()
    {
        for (int x = 0; x < puzzle_size; x++)
        {
            for (int y = 0; y < puzzle_size; y++)
                System.out.print(this.puzzle[x][y] +" ");
            System.out.println();
        }
        System.out.println();
    }

    public void move_left(int [][]pz)
    {
        find_piece(this.puzzle, 0);
        if (col - 1 > -1)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row][col - 1];
            copy_puzzle[row][col - 1] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child.move_cost = cal_move_cost(child.puzzle);
            child.direction = "left";
            child_nodes.add(child);
        }
    }

    public void move_right(int [][]pz)
    {
        find_piece(this.puzzle, 0);
        if (col + 1 < size)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row][col + 1];
            copy_puzzle[row][col + 1] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child.move_cost = cal_move_cost(child.puzzle);
            child.direction = "right";
            child_nodes.add(child);
        }
    }

    public void move_up(int [][]pz)
    {
        find_piece(this.puzzle, 0);
        if (row - 1 > -1)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row - 1][col];
            copy_puzzle[row - 1][col] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child.move_cost = cal_move_cost(child.puzzle);
            child.direction = "up";
            child_nodes.add(child);
        }
    }

    public void move_down(int [][]pz)
    {
        find_piece(this.puzzle, 0);
        if (row + 1 < size)
        {
            int [][]copy_puzzle = copy_puzzle(pz);
            int blank = copy_puzzle[row][col];

            copy_puzzle[row][col] = copy_puzzle[row + 1][col];
            copy_puzzle[row + 1][col] = blank;            
            node child = new node(copy_puzzle);
            child.parent = this;
            child.move_cost = cal_move_cost(child.puzzle);
            child.direction = "down";
            child_nodes.add(child);
        }
    }

    public void possible_moves()
    {
        move_left(this.puzzle);
        move_right(this.puzzle);
        move_up(this.puzzle);
        move_down(this.puzzle);
    }
}