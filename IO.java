import java.io.*;
import sun.security.ssl.SunJSSE;

public class IO extends program
{
    String file;
    static int puzzle_size = 0;
    BufferedReader br = null;
    FileReader fr = null;

    public int getPuzzleSize(String args) throws Exception
    {
        IO.this.file = args;
        String line;
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        
        line = br.readLine();
        if (line.contains(" "))
            puzzle_size = Integer.parseInt(line.substring(0, line.indexOf(" ")));
        else
            puzzle_size = Integer.parseInt(line);
        return (puzzle_size);
    }

    public int [][] getPuzzle(String file) throws Exception
    {
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line;
        int [][] puzzle = new int[puzzle_size][puzzle_size];
        int i = 0;
        int j;

        line = br.readLine();
        while ((line = br.readLine()) != null && i < puzzle_size)
        {
            if (line.isEmpty())
                continue ;
            else
            {
                String []row = line.split(" ");
                j = 0;
                for (String val:row)
                {
                    if (j < puzzle_size)
                    {
                        puzzle[i][j] = Integer.parseInt(val.trim());
                    }
                    j++;
                }
            }
            i++;
        }
        return (puzzle);
    }

    public int [][]getGoalState()
    {
        int [][]goal_state = new int [puzzle_size][puzzle_size];
        int val, max, i;

        max = puzzle_size * puzzle_size;
        val = 1;
        i = 0;
        while (val < max)
        {
            for (int j = 0; j < puzzle_size; j++)
            {
                if (val == max)
                    goal_state[i][j] = 0;
                else
                    goal_state[i][j] = val;
                val++;
            }
            i++;
        }
        return (goal_state);
    }
}