import java.util.*;

public class search
{
    /*private int getlowest_hval(LinkedList<node> children)
    {
        int lowest = 0;
        node child = children.getFirst();

        lowest = child.h_val;
        for (node c: children)
        {
            if (c.h_val < lowest)
                lowest = c.h_val;
        }
        return (lowest);
    }*/

    public LinkedList<node> breadthfirstsearch(node root)
    {
        LinkedList<node> solution_path = new LinkedList<node>();
        LinkedList<node> open_list = new LinkedList<node>();
        LinkedList<node> closed_list = new LinkedList<node>();
        IO io_class = new IO();

        open_list.add(root);
        boolean solution_found = false;

        while (!open_list.isEmpty() && !solution_found)
        {
            node current = open_list.get(0);
            closed_list.add(current);
            open_list.remove(0);
            current.possible_moves();

            for (int i = 0; i < current.child_nodes.size(); i++)
            {
                node current_child = current.child_nodes.get(i);
                System.out.println("openlist size: "+ open_list.size() + "childnodes: "+ current_child.child_nodes);
                if (current_child.is_goal())
                { 
                    System.out.println("Solution Found!");
                    solution_found = true;
                    path_to_solution(solution_path, current_child);
                    System.out.println("Found in "+ (solution_path.size() - 1) + " steps");
                }
                open_list.add(current_child);
                if (!contained(open_list, current_child) && !contained(closed_list, current_child))
                    open_list.add(current_child);
            }
        }
        return (solution_path);
    }

    private static void path_to_solution(LinkedList<node> path, node n)
    {
        while (n != null)
        {
            path.add(n);
            n = n.parent;
        }
    }

    private static boolean contained (LinkedList<node> list, node n)
    {
        for (int i = 0; i < list.size(); i++)
        {
            node x = list.get(i);
            if (x.same_puzzle(n.puzzle))
                return (true);
        }
        return (false);
    }
}