import java.time.LocalTime;

public class MinuteHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = time.getMinute() * 6.0 + time.getSecond() * 0.1;
    }
    @Override
    public String toSvg() {
        return String.format(
                "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" stroke=\"black\" stroke-width=\"2\" transform=\"rotate(%.2f)\"/>",
                angle
        );
    }
}
