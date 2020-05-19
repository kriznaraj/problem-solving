import java.util.Arrays;

public class minCostPath {
    public static void main(String[] args) {

        int[][] g = { { 0, 47, 8, 18, 1 }, { 43, 25, 39, 36, 13 }, { 22, 8, 13, 38, 46 }, { 41, 41, 40, 25, 44 },
                { 29, 43, 22, 50, 10 } };

        System.out.println(findMcp(g));
        System.out.println(findMcpR(g));
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

    static int findMcpR(int[][] g) {
        int[][] dp = new int[g.length][g[0].length];
        boolean[][] dir = new boolean[g.length][g[0].length];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int m = Integer.MAX_VALUE;
                boolean d = true;
                if (i > 0) {
                    m = dp[i - 1][j];
                    d = true;
                }
                if (j > 0 && dp[i][j - 1] < m) {
                    m = dp[i][j - 1];
                    d = false;
                }
                dp[i][j] = g[i][j] + m;
                dir[i][j] = d;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        for (int i = 0; i < dir.length; i++) {
            System.out.println(Arrays.toString(dir[i]));
        }

        int i = dp.length - 1;
        int j = dp[0].length -1;
        while(i != 0 || j != 0) {
            System.out.println(dir[i][j] ? "UP" : "DOWN");
            if(dir[i][j]) {
                i = i-1;
            } else {
                j = j-1;
            }
        }

        return dp[g.length - 1][g[0].length - 1];
    }


}