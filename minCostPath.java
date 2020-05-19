import java.util.Arrays;

public class minCostPath {
    public static void main(String[] args) {

        int[][] g = { { 0, 47, 8, 18, 1 }, { 43, 25, 39, 36, 13 }, { 22, 8, 13, 38, 46 }, { 41, 41, 40, 25, 44 },
                { 29, 43, 22, 50, 10 } };

        System.out.println(findMcp(g));
    }

    static int findMcp(int[][] g) {
        int[][] dp = new int[g.length][g[0].length];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int m = Integer.MAX_VALUE;
                if (i > 0) {
                    m = dp[i - 1][j];
                }
                if (j > 0 && dp[i][j - 1] < m) {
                    m = dp[i][j - 1];
                }
                dp[i][j] = g[i][j] + m;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[g.length - 1][g[0].length - 1];
    }
}