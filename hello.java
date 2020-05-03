import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class hello {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // Date dt = new Date(1587272890);
        

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        System.out.println( sdf.format(new Date(1587272890000L)));

        getMillies();
    }

    private static void getMillies() {

        // ZonedDateTime startOfDay = ZonedDateTime.ofInstant( Instant.ofEpochSecond( 1466058808L ) , ZoneId.of( "UTC" ) )
        //         .toLocalDate().atStartOfDay( ZoneId.of( getConfigProvider().getCbTimezone()));

        long totalSeconds = ZonedDateTime.now(ZoneId.of("PST8PDT")).getOffset().getTotalSeconds();


        TimeZone tz = TimeZone.getTimeZone("PST8PDT");
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        System.out.println();
    }
}