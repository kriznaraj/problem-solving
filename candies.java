import java.util.Arrays;

public class candies {
    // https://www.hackerrank.com/challenges/candies/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    public static void main(String[] args) {
        // int[] a = new int[] { 4, 6, 4, 5, 6, 2 };
        int[] a = new int[] { 1, 2, 2 };
        System.out.println(findMinCandiesToBuy(a));
    }

    static long findMinCandiesToBuy(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        long[] dp = new long[a.length];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {

            if (a[i] > a[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }

        System.out.println(Arrays.toString(dp));

        return Arrays.stream(dp).sum();
    }

}