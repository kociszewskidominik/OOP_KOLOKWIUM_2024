import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public abstract class Clock {
    private int hours;
    private int minutes;
    private int seconds;
    private City city;

    public Clock() {
        
    }

    public Clock(City city) {
    }

    protected int getHours() {
        return hours;
    }
    protected int getMinutes() {
        return minutes;
    }
    protected int getSeconds() {
        return seconds;
    }

    public Clock(int hours, int minutes, int seconds, City city) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.city = city;
    }

    public void setTime(int hours, int minutes, int seconds){
        if(hours < 0 || hours > 23){
            throw new IllegalArgumentException("Godzina nie mieści się w zakresie");
        } else if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minuty nie mieszczą się w zakresie");
        } else if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Sekundy nie mieszczą się w zakresie");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.of(hours, minutes, seconds);
        formatter.format(time);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void setCity(City ncity){
        int staraStrefa = city.getStrefaCzasowa();
        int nowaStrefa = ncity.getStrefaCzasowa();

        this.city = ncity;

        int roznica = nowaStrefa - staraStrefa;

        int oldHour = getHours();
        int oldMinutes = getMinutes();
        int oldSeconds = getSeconds();

        int newHour = (oldHour + roznica + 24) % 24;

        setTime(newHour, oldMinutes, oldSeconds);
    }
}
