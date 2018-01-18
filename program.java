import java.io.*;
import java.util.*;

import jdk.nashorn.internal.runtime.regexp.joni.ast.Node;

public class program
{
    static IO io_class = new IO();
    static search s_class = new search();
    static int puzzle_size = 0;

    static int errorHandling(int [][]puzzle)
    {
        if (puzzle == null || (puzzle_size < 3 || puzzle_size > 5)) //Error handling
        {
            if (puzzle_size < 3 || puzzle_size > 5)
                System.out.println("Program only supports 3x3, 4x4 and 5x5 dimensions");
            System.out.println("Invalid puzzle format");
            return -1;
        }
        return (0);
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length == 2)
        {
            puzzle_size = io_class.getPuzzleSize(args[1]);
            int [][]init_puzzle = io_class.getPuzzle(args[1]);
            int [][]goal_state = io_class.getGoalState();
            LinkedList<node> solution = new LinkedList<node>();
            String heuristic_function = args[0];

            if (errorHandling(init_puzzle) == -1)
                return ;
            node root = new node(init_puzzle);
            if (heuristic_function.equals("bfs"))
            {
                solution = s_class.breadthfirstsearch(root);
                if (solution.size() == 0)
                    System.out.println("No Solution found!");
            }
            else
                System.out.println("Heuristic function not found");
        }
        else
        {
            System.out.println("usage: java program <heuristic function> <puzzle input file>");
            System.out.println("       Available heuristics: 'bfs' / 'ida' / 'amo'");
        }
    }
}