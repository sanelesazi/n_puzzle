import java.io.*;
import java.util.*;

public class search
{
    public LinkedList<node> breadthfirstsearch(node root, String heuristic_func) throws Exception
    {
        LinkedList<node> solution_path = new LinkedList<node>(); //will save the solution path
        LinkedList<node> open_list = new LinkedList<node>(); //stores nodes that are being inspected
        LinkedList<node> closed_list = new LinkedList<node>(); //stores nodes that have been inspected
        IO io_class = new IO();
        timeprinter tp = new timeprinter();
        boolean solution_found = false;

        open_list.add(root);
        root.printpuzzle();
        tp.start_timer();
        System.out.println("Searching...");

        if (root.is_goal()) //checks if the puzzle is already at goal state
            solution_path = open_list;
        while (!solution_found && !open_list.isEmpty() && !root.is_goal())
        {
            node current = open_list.get(0);
            closed_list.add(current);
            open_list.remove(0);
            current.expand_node(heuristic_func);

            int lowest_move_cost = get_lowest_move_cost(current.child_nodes);
            for (int i = 0; i < current.child_nodes.size(); i++)
            {
                node current_child = current.child_nodes.get(i);
                if (current_child.move_cost > lowest_move_cost) //ignores the node if it has a high f value
                    continue ;
                if (current_child.is_goal())
                {
                    path_to_solution(solution_path, current_child);
                    solution_found = true;
                }
                if (!contained(open_list, current_child) && !contained(closed_list, current_child)) //if the node has never been inspected before
                    open_list.add(current_child);                                                   //add the node to be inspected
            }
        }
        tp.stop_timer();
        return (solution_path);
    }

    private static int get_lowest_move_cost(LinkedList<node> children) //This returns the lowest f value amongst the children nodes
    {
        int lowest = 0;

        if (children.size() > 0)
            lowest = children.get(0).move_cost;
        for (node x: children)
        {
            if (x.move_cost < lowest)
                lowest = x.move_cost;
        }
        return (lowest);
    }

    private static void path_to_solution(LinkedList<node> path, node n) //this inserts the nodes that lead to the solution into the linkedlist path
    {
        while (n != null)
        {
            path.add(n);
            n = n.parent;
        }
    }

    private static boolean contained (LinkedList<node> list, node n) //this is meant to check if node n exists in the linkedlist list
    {                                                                //For some odd reason it doesn't, or the problem is somewhere else - debug needed
        for (int i = 0; i < list.size(); i++)
        {
            node x = list.get(i);
            if (x.same_puzzle(n.puzzle))
                return (true);
        }
        return (false);
    }
}