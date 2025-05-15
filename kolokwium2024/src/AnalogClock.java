import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class AnalogClock extends Clock {
    private final List<ClockHand> hands = Arrays.asList(
            new HourHand(), new MinuteHand(), new SecondHand()
    );

    public AnalogClock(City city) {
        super(city);
    }

    public void toSvg(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\">\n");
        sb.append("<circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\"/>\n");

        LocalTime time = LocalTime.of(getHours(), getMinutes(), getSeconds());
        for (ClockHand hand : hands) {
            hand.setTime(time);
            sb.append(hand.toSvg()).append("\n");
        }

        sb.append("</svg>");
        Files.write(Paths.get(filePath), sb.toString().getBytes());
    }
}
