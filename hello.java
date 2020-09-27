import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class hello {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // Date dt = new Date(1587272890);
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        // System.out.println( sdf.format(new Date(1587272890000L)));
        Double d1 = 14.57d;
        Double d2 = -1.46d;
        Double d3 = 36.99d;
        Double d4 = -3.7d;
        Double sum = d1 + d2 + d3 + d4;

        System.out.println(sum);

        System.out.println(findTime(5));
    }

    static int findTime(int n) {
        int count = 0;
        int totalTime = 0;
        do {
            int time = (int) Math.pow(2, count);
            totalTime +=time;
            System.out.println(time);
            count++;
        } while(count <= n);
        return totalTime;
    }
    //We thought of adding exponential 
}