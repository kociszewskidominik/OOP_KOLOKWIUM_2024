import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class City {
    private String stolica;
    private int strefaCzasowa;
    private String szerokoscGeograficzna;
    private String dlugoscGeograficzna;

    public City(String stolica, int strefaCzasowa, String szerokoscGeograficzna, String dlugoscGeograficzna) {
        this.stolica = stolica;
        this.strefaCzasowa = strefaCzasowa;
        this.szerokoscGeograficzna = szerokoscGeograficzna;
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public String getStolica() {
        return stolica;
    }

    public int getStrefaCzasowa() {
        return strefaCzasowa;
    }

    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    private static City parseLine(String line) {
        line = line.trim();
        String[] tokens = line.split(",");
        if (tokens.length < 4) {
            throw new IllegalArgumentException("Nieprawidłowa linia: " + line);
        }
        String name = tokens[0];
        int zone = Integer.parseInt(tokens[1]);
        String lat = tokens[2];
        String lon = tokens[3];
        return new City(name, zone, lat, lon);
    }

    public static Map<String, City> parseFile(String path) throws IOException {
        Map<String, City> mapa = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                City city = parseLine(line);
                String key = city.getStolica();
                if (mapa.containsKey(key)) {
                    throw new IllegalArgumentException("Duplikat stolicy: " + key);
                }
                mapa.put(key, city);
            }
        }
        return mapa;
    }

    public LocalTime localMeanTime(LocalTime zoneTime) {
        String text = dlugoscGeograficzna.trim();
        char dir = text.charAt(text.length() - 1);
        double degrees = Double.parseDouble(text.substring(0, text.length() - 1));
        if (dir == 'W' || dir == 'w') degrees = -degrees;

        double offsetHours = degrees / 15.0;
        int hOff = (int) offsetHours;
        double fractional = Math.abs(offsetHours - hOff) * 60;
        int mOff = (int) fractional;
        int sOff = (int) ((fractional - mOff) * 60);

        return zoneTime
                .plusHours(hOff)
                .plusMinutes(offsetHours >= 0 ? mOff : -mOff)
                .plusSeconds(offsetHours >= 0 ? sOff : -sOff);
    }

    public static int worstTimezoneFit(City c1, City c2) {
        double diff1 = Math.abs(parseLongitudeHours(c1) - c1.getStrefaCzasowa());
        double diff2 = Math.abs(parseLongitudeHours(c2) - c2.getStrefaCzasowa());
        return Double.compare(diff2, diff1);
    }

    private static double parseLongitudeHours(City c) {
        String text = c.getDlugoscGeograficzna().trim();
        char dir = text.charAt(text.length() - 1);
        double deg = Double.parseDouble(text.substring(0, text.length() - 1));
        if (dir == 'W' || dir == 'w') deg = -deg;
        return deg / 15.0;
    }
}