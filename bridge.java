import java.util.Arrays;

public class bridge {
    public static void main(String[] args) {
        // int[] x = {6, 4, 2,1};
        // int[] y = {2,3,6,5};

        int[] x = { 8, 1, 4, 3, 5, 2, 6, 7 };
        int[] y = { 1, 2, 3, 4, 5, 6, 7, 8 };

        System.out.println(findMaxBridge(x, y));
    }

    static int findMaxBridge(int[] x, int[] y) {

        int[] dp = new int[x.length];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                int b = dp[j];
                if ((x[i] <= x[j] && y[i] <= y[j]) || (x[i] >= x[j] && y[i] >= y[j])) {
                    max = Math.max(max, b + 1);
                }
            }
            dp[i] = max;
        }

        System.out.println(Arrays.toString(dp));
        return Arrays.stream(dp).max().getAsInt();
    }
}