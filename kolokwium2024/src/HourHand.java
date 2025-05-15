import java.time.LocalTime;

public class HourHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = (time.getHour() % 12) * 30.0
                + time.getMinute() * 0.5
                + time.getSecond() * (0.5 / 60.0);
    }
    @Override
    public String toSvg() {
        return String.format(
                "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" stroke=\"black\" stroke-width=\"4\" transform=\"rotate(%.2f)\"/>",
                angle
        );
    }
}
