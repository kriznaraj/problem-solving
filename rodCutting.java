import java.util.Arrays;

public class rodCutting {
    public static void main(String[] args) {
        int[] price = new int[] {0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30};
        int l = 4;
        System.out.println(findProfit(l, price));
        System.out.println(findProfitT(l, price));
    }

    static int findProfit(int l, int[] p) {
        if(l == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= l && i < p.length; i++) {
            int profit = findProfit(l-i, p) + p[i];
            if(profit > max) {
                max = profit;
            }
        }
        return max;
    }

    static int findProfitT(int l, int[] p) {
        int[] dp = new int[l + 1];
        dp[0] = 0;
        int[] cut = new int[l+1];

        for (int i = 1; i < dp.length; i++) {
            int max = Integer.MIN_VALUE;
            int c = 0;
            for (int j = 0; j <= i && j < p.length ; j++) {
                int profit = dp[i-j] + p[j];
                if(profit > max) {
                    max = profit;
                    c = j;
                }
            }
            dp[i] = max;
            cut[i] = c;
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(cut));

        int i = l;
        while(i > 0) {
            System.out.println(String.format("%d => %d", cut[i], p[cut[i]]));
            i = i - cut[i];
        }

        return dp[l];
    }
}