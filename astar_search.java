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
        
        open_list.add(root);
        while (!open_list.isEmpty() && !solution_found)
        {
            node current = open_list.get(0);
            opened++;
            open_list.remove();
            closed_list.add(current);
            current.expand_node();
            for (node child: current.child_nodes)
            {
                child.calc_heuristic_value(heuristic_func);
                if (child.is_goal())
                {
                    path_to_solution(solution_path, child);
                    solution_found = true;
                    //return (solution_path);
                }
                else if (!contained(open_list, child) && !contained(closed_list, child))
                    open_list.add(child);
                else if (contained(open_list, child))
                {
                    continue ;
                    /*if (open_list.get(index).g_value < child.g_value)
                        continue ;
                    else
                    {
                        open_list.remove(index);
                        open_list.add(child);
                    }*/
                }
                else if (contained(closed_list, child))
                {
                    if (closed_list.get(index).g_value < child.g_value)
                        continue ;
                    else
                    {
                        //closed_list.remove(index);
                        //open_list.add(child);
                    }
                }
            }
            reorder_list(open_list);
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
        while (n != null)
        {
            path.add(n);
            n = n.parent;
        }
    }
}

class compare_f_value implements Comparator<node>
{
    public int compare (node x1, node x2)
    {
        if (x1.f_value > x2.f_value)
            return (1);
        else if (x1.f_value == x2.f_value)
        {
            if (x1.g_value > x2.g_value)
                return (1);
        }
        else
            return (-1);
        return (0);
    }
}