import java.util.*;

public class timeprinter extends program
{ 
    private static int counter = 0; 
    public void timer() 
    { 
        Timer timer = new Timer(); 
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            { 
                counter++; 
                System.out.print(counter +" "); 
            } 
        }, new Date(), 1000);
    } 
} 