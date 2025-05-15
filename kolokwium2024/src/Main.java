import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, City> map   = City.parseFile("cities.csv");
        List<City>      list    = Arrays.asList(map.values().toArray(new City[0]));
        AnalogClock     clock   = new AnalogClock(list.get(0));
    }
}
