import java.util.*;

public class timeprinter extends program
{ 
    private static int counter = 0; 
    public static void timer() 
    { 
        Timer timer = new Timer(); 
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            { 
                counter++; 
                System.out.println(counter); 
            } 
        }, new Date(), 1000); 
    } 
} 