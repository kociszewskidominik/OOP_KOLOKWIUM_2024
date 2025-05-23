import java.time.LocalTime;

public class SecondHand extends ClockHand {
    @Override
    public void setTime(LocalTime time) {
        angle = time.getSecond() * 6.0;
    }
    @Override
    public String toSvg() {
        return String.format(
                "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-80\" stroke=\"red\" stroke-width=\"1\" transform=\"rotate(%.2f)\"/>",
                angle
        );
    }
}
