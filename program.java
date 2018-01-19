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
            LinkedList<node> solution = new LinkedList<node>();
            String heuristic_function = args[0];

            if (errorHandling(init_puzzle) == -1)
                return ;
            node root = new node(init_puzzle);
            if (heuristic_function.equals("man") || heuristic_function.equals("ham"))
            {
                System.out.println(puzzle_size + " x " + puzzle_size +" Puzzle Found");
                System.out.println();
                solution = s_class.breadthfirstsearch(root, heuristic_function);
                if (solution.size() == 0)
                {
                    System.out.println("No Solution found! - Suggestions:");
                    System.out.println("Try different heuristic function OR Solution may not exist at all");
                }
            }
            else
            {
                System.out.println("Heuristic function not found!");
                System.out.println("    Try 'man' / 'ham' / 'undecided'");
            }
        }
        else
        {
            System.out.println("usage: java program <heuristic function> <puzzle input file>");
            System.out.println("       Available heuristics: 'man' / 'ham' / 'amo'");
            System.out.println("       'man': Manhattan Distance");
            System.out.println("       'ham': Hamming Distance");
            System.out.println("       'amo': Still Deciding Distance");
        }
    }
}