import java.util.Arrays;

public class nQuebes {
    public static void main(String[] args) {
        int x = 3;
        int[] c = new int[] {0, 20, 30, 40, 25, 15, 20, 28};
        int[] m = new int[c.length];
        System.out.println(minCost(x, c, c.length - 1));
        System.out.println(minCostM(x, c, c.length - 1, m));
        System.out.println(minCostT(x, c));
        System.out.println(minCostTReconstruct(x, c));
    }

    static int minCost(int x, int[] c, int i) {
        if(i == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= x && i -j >= 0; j++) {
            int cost = c[i] + minCost(x, c, i -j);
            if(cost < min) {
                min = cost;
            }
        }
        return min;
    }

    static int minCostM(int x, int[] c, int i, int[] mem) {
        if(i == 0) {
            return 0;
        }

        if(mem[i] != 0) {
            return mem[i];
        }

        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= x && i -j >= 0; j++) {
            int cost = c[i] + minCost(x, c, i -j);
            if(cost < min) {
                min = cost;
            }
        }
        mem[i] = min;
        return min;
    }

    static int minCostT(int x, int[] c) {

        int[] dp = new int[c.length];
        dp[0] = c[0];

        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= Math.min(i, x); j++) {
                int cost = dp[i-j] + c[i];
                if( cost < min) {
                    min = cost;
                }
            }
            dp[i] = min;
        }

        System.out.println(Arrays.toString(dp));
        return dp[c.length - 1];
    }

    static int minCostTReconstruct(int x, int[] c) {

        int[] dp = new int[c.length];
        int[] preList = new int[c.length];
        dp[0] = c[0];

        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            int pre = -1;
            for (int j = 1; j <= Math.min(i, x); j++) {
                int cost = dp[i-j] + c[i];
                if( cost < min) {
                    min = cost;
                    pre = j;
                }
            }
            preList[i] = pre;
            dp[i] = min;
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(preList));


        int i = preList.length - 1;
        while(i > 0) {
            System.out.println(String.format("Jump to %d => %d", i, c[i]));
            i = i - preList[i];
        }

        return dp[c.length - 1];
    }
}