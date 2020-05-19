import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class hello {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // Date dt = new Date(1587272890);
        

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        System.out.println( sdf.format(new Date(1587272890000L)));
    }
}