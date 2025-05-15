public class DigitalClock extends Clock{
    public enum TimeFormat{
        TWENTY_FOUR_HOUR,
        TWELVE_HOUR;
    }

    private TimeFormat format;
    private int hh = getHours();
    private int mm = getMinutes();
    private int ss = getSeconds();

    public DigitalClock(TimeFormat format) {
        super();
        this.format = format;
    }

    @Override
    public String toString(){
        if(format == TimeFormat.TWELVE_HOUR){
            String suffix = "";
            int displayedHour = hh % 12;

            if(displayedHour == 0){
                displayedHour = 12;
            }

            if(hh < 12){
                suffix = "AM";
            } else {
                suffix = "PM";
            }

            String fmt = String.format("%02d:%02d:%02d", displayedHour, mm, ss);
            return fmt + suffix;
        }
        if(format == TimeFormat.TWENTY_FOUR_HOUR){
            return super.toString();
        }

        return null;
    }
}
