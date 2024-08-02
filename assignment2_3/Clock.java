import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    public void displayCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        
        while (true) {
            Date date = new Date();
            System.out.println(formatter.format(date));
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.out.println("Clock thread interrupted.");
            }
        }
    }
}

