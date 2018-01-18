import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public LinkedList<node> breadthfirstsearch(node root) throws Exception
    {
        LinkedList<node> solution_path = new LinkedList<node>();
        LinkedList<node> open_list = new LinkedList<node>();
        LinkedList<node> closed_list = new LinkedList<node>();
        IO io_class = new IO();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String prompt = "";

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

    private static void print_solution(LinkedList<node> solution)
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