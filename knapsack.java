import java.util.Arrays;

public class knapsack {
    public static void main(String[] args) {
        int w = 5;
        int[] v = new int[] { 60, 100, 120 }; //{ 4, 14, 10, 5 };
        int[] wt = new int[] { 1, 2, 3 }; //{ 3, 7, 10, 6 };

        System.out.println(ks(w, v, wt, wt.length - 1, new int[w+1][wt.length]));
        System.out.println(ks(w, v, wt, wt.length));
        System.out.println(ks_reconstruct(w, v, wt, wt.length));
    }

    // memoization
    static int ks(int w, int[] v, int[] wt, int i, int[][] memo) {
        if (w == 0 || i == -1) {
            return 0;
        }

        if (memo[w][i] != 0) {
            return memo[w][i];
        }
        if (w >= wt[i]) {
            int pick = v[i] + ks(w - wt[i], v, wt, i - 1, memo);
            int dont = ks(w, v, wt, i - 1, memo);
            int res = Math.max(pick, dont);
            memo[w][i] = res;
        } else {
            int res = ks(w, v, wt, i - 1, memo);
            memo[w][i] = res;
        }
        return memo[w][i];
    }

    // bottomup
    // consider i starts with 1
    static int ks(int weight, int[] v, int[] wt, int index) {

        int[][] dp = new int[weight+1][index+1];

        for (int w = 0; w < dp.length; w++) {
            for (int i = 0; i < dp[w].length; i++) {

                if (w == 0 || i == 0) {
                    dp[w][i] = 0;
                    continue;
                }

                if (w >= wt[i - 1]) {
                    int incl = v[i-1] + dp[w - wt[i - 1]][i - 1];
                    int dicl = dp[w][i - 1];
                    dp[w][i] = Math.max(incl, dicl);
                } else {
                    dp[w][i] = dp[w][i - 1];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[weight][index];
    }

    static int ks_reconstruct(int weight, int[] v, int[] wt, int index) {

        int[][] dp = new int[weight+1][index+1];
        boolean[][] pick = new boolean[weight+1][index+1];

        for (int w = 0; w < dp.length; w++) {
            for (int i = 0; i < dp[w].length; i++) {

                if (w == 0 || i == 0) {
                    dp[w][i] = 0;
                    continue;
                }

                if (w >= wt[i - 1]) {
                    int incl = v[i-1] + dp[w - wt[i - 1]][i - 1];
                    int dicl = dp[w][i - 1];
                    if(incl > dicl) {
                        pick[w][i] = true;
                    }
                    dp[w][i] = Math.max(incl, dicl);
                } else {
                    dp[w][i] = dp[w][i - 1];
                }
            }
        }


        int w = weight;
        int i = index;

        while(w >= 0 && i >= 0) {
            if(pick[w][i]) {
                System.out.println("Pick: " + wt[i-1] + " | " + v[i-1]);
                w = w-wt[i-1];
                i--;
            } else {
                i--;
            }
        }
        return dp[weight][index];
    }
}