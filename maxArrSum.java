import java.util.Arrays;

public class maxArrSum {
    //https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    public static void main(String[] args) {
        // int[] a = new int[] { 3, 7, 4, 6, 5 };
        int[] a = new int[] { -2, 1, 3, -4, 5 };
        System.out.println(findMaxSum(a));
    }

    static int findMaxSum(int[] a) {
        int[] dp = new int[a.length + 1];
        dp[1] = a[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(a[i - 1], Math.max(dp[i - 2] + a[i - 1], dp[i - 1]));
        }
        System.out.println(Arrays.toString(dp));
        return dp[a.length];
    }
}