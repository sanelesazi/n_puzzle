import java.util.*;

public class timeprinter extends program
{ 
    private static int counter = 0; 
    Timer timer = new Timer();

    public void start_timer() 
    { 
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            { 
                counter++; 
                System.out.print(counter +"s "); 
            } 
        }, new Date(), 1000);
    }
    public void stop_timer()
    {
        timer.cancel();
    }
} 