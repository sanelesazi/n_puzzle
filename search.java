import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class search
{
    public LinkedList<node> breadthfirstsearch(node root) throws Exception
    {
        LinkedList<node> solution_path = new LinkedList<node>(); //will save the solution path
        LinkedList<node> open_list = new LinkedList<node>(); //stores nodes that are being inspected
        LinkedList<node> closed_list = new LinkedList<node>(); //stores nodes that have been inspected
        IO io_class = new IO();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean solution_found = false;
        String prompt = "";

        open_list.add(root);

        while (!open_list.isEmpty() && !solution_found)
        {
            node current = open_list.get(0);
            closed_list.add(current);
            open_list.remove(0);
            current.possible_moves();

            int lowest_move_cost = get_lowest_move_cost(current.child_nodes);
            for (int i = 0; i < current.child_nodes.size(); i++)
            {
                node current_child = current.child_nodes.get(i);
                if (current_child.move_cost > lowest_move_cost) //ignores the node if it has a high f value
                    continue ;
                if (current_child.is_goal())
                {
                    System.out.println("Solution Found!");
                    System.out.println("Would you like to print the solution? (y / n)");
                    path_to_solution(solution_path, current_child);
                    prompt = br.readLine();
                    if (prompt.equals("y") || prompt.equals("yes"))
                    {
                        System.out.println("yes");
                        print_solution(solution_path);
                    }
                    solution_found = true;
                    System.out.println("Solution found in "+ (solution_path.size() - 1) + " steps");
                }
                if (!contained(open_list, current_child) && !contained(closed_list, current_child)) //if the node has never been inspected before
                    open_list.add(current_child);                                                   //add the node to be inspected
            }
        }
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
}