import java.io.*;
import java.util.LinkedList;
import java.lang.*;

public class program
{
    public static class node
    {
        int [][]puzzle;
        int h_val = 0;
        node left;
        node right;
        node up;
        node down;
    }

    public static void main(String []args) throws Exception
    {
        IO io_class = new IO();
        heuristic_val h_class = new heuristic_val();
        node open_state = new node();
        String []direction = {"left","right","up","down"};
        LinkedList<node> head = new LinkedList<node>();

        io_class.puzzle_size = io_class.getPuzzleSize(args[0]);
        open_state.puzzle = io_class.getPuzzle(args[0]);
        open_state.h_val = h_class.get_heuristicval(open_state.puzzle);
        
        System.out.println("initial puzzle h_val: "+open_state.h_val);
        for (String dir: direction)
        {
            if (h_class.get_move(open_state.puzzle, dir) == 1) //checks if can move left,right,up or down
            {
                switch(dir){
                    case "left": open_state.left = new node();
                        open_state.left.h_val = h_class.get_heuristicval(open_state.left.puzzle = h_class.move(dir, open_state.puzzle));
                        System.out.println("left h_val: "+open_state.left.h_val);
                        break ;
                    case "right":
                        open_state.right = new node();
                        open_state.right.h_val = h_class.get_heuristicval(open_state.right.puzzle = h_class.move(dir, open_state.puzzle));
                        System.out.println("right h_val: "+open_state.right.h_val);
                        break ;
                    case "up": open_state.up = new node();
                        open_state.up.h_val = h_class.get_heuristicval(open_state.up.puzzle = h_class.move(dir, open_state.puzzle));    
                        System.out.println("up h_val: "+open_state.up.h_val);
                        break ;
                    case "down": open_state.down = new node();
                        open_state.down.h_val = h_class.get_heuristicval(open_state.down.puzzle = h_class.move(dir, open_state.puzzle));    
                        System.out.println("down h_val: "+open_state.down.h_val);
                        break ;
                }
            }
        }
        head.add(open_state);
        for (node pz: head)
        {
            System.out.println(pz.h_val);
        }
    }
}