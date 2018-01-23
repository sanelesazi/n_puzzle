import java.util.*;

public class astar_search extends program
{
    public int opened = 0;
    private static int index = 0;

    public LinkedList<node> algorithm (node root, String heuristic_func)// throws Exception
    {
        LinkedList<node> solution_path = new LinkedList<node>(); //will save the solution path
        LinkedList<node> open_list = new LinkedList<node>(); //stores a queue of nodes that will be inspected
        LinkedList<node> closed_list = new LinkedList<node>(); //stores nodes that have been inspected
        boolean solution_found = false;

        if (is_solvable(root.puzzle))
        {
            open_list.add(root);
            while (!open_list.isEmpty() && !solution_found)
            {
                node current = open_list.getFirst();
                opened++;
                closed_list.add(current);
                open_list.removeFirst();
                if (current.is_goal())
                {
                    path_to_solution(solution_path, current);
                    solution_found = true;
                }
                else{
                current.expand_node();
                for (node child: current.child_nodes)
                {
                    child.calc_heuristic_value(heuristic_func);
                    if (contained(closed_list, child))
                    {
                        if (closed_list.get(index).g_value > child.g_value)
                        {
                            open_list.add(child);
                        }
                    }
                    else if (contained(open_list, child))
                    {
                        if (open_list.get(index).g_value > child.g_value)
                        {
                            open_list.remove(index);
                            open_list.add(child);
                        }
                    }
                    else if (!contained(open_list, child) && !contained(closed_list, child))
                        open_list.add(child);
                }
                reorder_list(open_list);
            }
            }
        }
        return (solution_path);
    }

    private static void reorder_list(LinkedList<node> list)
    {
        Collections.sort(list, new compare_f_value());
    }

    private static boolean contained (LinkedList<node> list, node n)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (n.same_puzzle(list.get(i).puzzle))
            {
                index = i;
                return (true);
            }
        }
        return (false);
    }

    private static void path_to_solution(LinkedList<node> path, node n) //this inserts the nodes that lead to the solution into the linkedlist path
    {
        int counter = 1;
        while (n != null)
        {
            path.add(n);
            n = n.parent;
            System.out.println("path: "+ counter++);
        }
    }
    private static int inverse_count(int []pz)
    {
        int inv_counter = 0;
        for (int i = 0; i < (puzzle_size * puzzle_size) - 1; i++)
        {
            for (int j = i + 1; j < (puzzle_size * puzzle_size); j++)
                if (pz[i] > pz[j])
                    inv_counter++;
        }
        return (inv_counter);
    }

    public boolean is_solvable(int [][]puzzle)
    {
        int []checker_puzzle = new int [puzzle_size * puzzle_size];
        int i = -1;
        
        for (int x = 0; x < puzzle_size; x++)
        {
            for (int y = 0; y < puzzle_size; y++)
                checker_puzzle[++i] = puzzle[x][y];
        }
        i = inverse_count(checker_puzzle);
        if (i % 2 != 0)
            return (true);
        else
            return (false);
    }
}

class compare_f_value implements Comparator<node>
{
    public int compare (node x1, node x2)
    {
        if (x1.h_value > x2.h_value)
            return (1);
        else if (x1.h_value == x2.h_value)
        {
            if (x1.g_value > x2.g_value)
                return (1);
        }
        else
            return (-1);
        return (0);
    }
}