import java.io.*;
import java.util.*;

public class program
{
    static IO io_class = new IO();
    static int puzzle_size = 0;

    static int check_dup_n_range(int [][]puzzle)
    {
        int max_num = (puzzle_size * puzzle_size) - 1;
        int []numbers = new int[(puzzle_size * puzzle_size)];

        for (int x = 0; x < puzzle_size; x++)
        {
            for (int y = 0; y < puzzle_size; y++)
            {
                if (puzzle[x][y] > max_num || puzzle[x][y] < 0)
                    return (1);
                if (numbers[puzzle[x][y]] == 1)
                    return (1);
                numbers[puzzle[x][y]] = 1;
            }
        }
        return (0);
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
        if (check_dup_n_range(puzzle) == 1)
        {
            System.out.println("Error - Check for duplicates or puzzle numbers out of range");
            return -1;
        }
        return (0);
    }

    private static void print_solution(LinkedList<node> solution) //this simply prints the nodes that lead to the solution
    {
        int list_size = solution.size() - 1;
        while (list_size > -1)
        {
            System.out.println(solution.get(list_size).direction);
            solution.get(list_size).printpuzzle();
            list_size--;
        }
    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes)
    {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length == 2)
        {
            astar_search astar = new astar_search();
            if ((puzzle_size = io_class.getPuzzleSize(args[1])) == -1)
                return ;
            int [][]init_puzzle = io_class.getPuzzle(args[1]);
            LinkedList<node> solution = new LinkedList<node>();
            String heuristic_function = args[0];
            String prompt = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            char s = 's';
            double stop_timer;
            double start_timer = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();

            if (errorHandling(init_puzzle) == -1)
                return ;
            node root = new node(init_puzzle);
            if (heuristic_function.equals("man") || heuristic_function.equals("ham") || heuristic_function.equals("lin"))
            {
                switch(heuristic_function)
                {
                    case "man": System.out.println("Selected: Manhattan Distance");
                    break ;
                    case "ham": System.out.println("Selected: Hamming Distance");
                    break ;
                    case "lin": System.out.println("Selected: Linear Conflict");
                    break ;
                }
                System.out.println(puzzle_size + " x " + puzzle_size +" Puzzle Found\n");
                root.printpuzzle();
                System.out.println("Searching...");
                solution = astar.algorithm(root, heuristic_function); //solution linkedlist saves the nodes that made it to goal
                if (solution.size() == 0)
                {
                    stop_timer = System.currentTimeMillis();
                    System.out.println("No Solution found! - Suggestions:");
                    System.out.println("Try different heuristic function OR Solution may not exist at all");
                }
                else
                {
                    stop_timer = System.currentTimeMillis();
                    System.out.println("Solution Found!");
                    System.out.println("Would you like to print the solution? (y / n)");
                    prompt = br.readLine();
                    if (prompt.equals("y") || prompt.equals("yes"))
                        print_solution(solution);
                    if (solution.size() == 2) //if only 2 steps, meaning 1 step, and not STEPS - this is jut for print out purposes (1 STEP || more than one STEPS)
                        s = ' ';
                    System.out.println("Opened states: "+ astar.opened);
                    System.out.println("Solution found in "+ (solution.size() - 1) + " move"+ s); //this will print MOVES if more than 1, and STEP if only 1
                }
                long memory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("used memory in bytes: "+ memory);
                System.out.println("used memory in megabytes: "+ bytesToMegabytes(memory));
                System.out.println("time elapsed: "+ (stop_timer - start_timer)/1000 +"s");
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
            System.out.println("       Available heuristics: 'man' / 'ham' / 'lin'");
            System.out.println("       'man': Manhattan Distance");
            System.out.println("       'ham': Hamming Distance");
            System.out.println("       'lin': Linear Conflict Distance");
        }
    }
}