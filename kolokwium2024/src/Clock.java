import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public abstract class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    public void setCurrentTime(){
        LocalTime time = LocalTime.now();
    }

    public void setTime(int hours, int minutes, int seconds){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.of(hours, minutes, seconds);
        formatter.format(time);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
