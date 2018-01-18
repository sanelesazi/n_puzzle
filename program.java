import java.io.*;
import java.util.*;

public class program
{
    static IO io_class = new IO();
    static search s_class = new search();
    static int puzzle_size = 0;

    static void printpuzzle(int [][]pz)
    {
        for (int x = 0; x < puzzle_size; x++)
        {
            for (int y = 0; y < puzzle_size; y++)
                System.out.print(pz[x][y] +" ");
            System.out.println("");
        }
    }
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
            String heuristic_function = args[0];

            errorHandling(init_puzzle);
            node root = new node(init_puzzle);
            if (heuristic_function.equals("bfs"))
                s_class.breadthfirstsearch(root);
            else
                System.out.println("Heuristic function not found");
            //root.possible_moves();
            
            /*System.out.println("nodes: "+root.child_nodes);
            for (node x: root.child_nodes)
            {
                printpuzzle(x.puzzle);
                System.out.println();
            }*/
        }
        else
            System.out.println("error");
    }
}