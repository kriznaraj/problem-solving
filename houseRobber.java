import java.util.Arrays;

public class houseRobber {
    public static void main(String[] args) {

        int[] s = new int[] {2, 25, 20, 15, 10, 15, 25, 18, 16, 100, 10};
        boolean[] rob = new boolean[s.length];
        int[] mem = new int[s.length];

        System.out.println(findMaxProfit(s, mem, rob, s.length-1));
        System.out.println(Arrays.toString(rob));
        System.out.println(Arrays.toString(mem));

        System.out.println(findMaxProfitT(s));
        System.out.println(findMaxProfitTReconstrct(s));
    }

    static int findMaxProfit(int[] s, int[] mem, boolean[] robbed, int i) {
        if(i == 0) {
            robbed[0] = true;
            return s[0];
        }

        if(i < 0) {
            return 0;
        }

        if(mem[i] != 0) {
            return mem[i];
        }

        int rob = s[i] + findMaxProfit(s, mem, robbed, i-2);
        int dont = findMaxProfit(s, mem, robbed, i-1);

        if(rob > dont) {
            robbed[i] = true;
            mem[i] = rob;
            return rob;
        } else {
            mem[i] = dont;
            return dont;
        }
    }

    static int findMaxProfitT(int[] s) {
        int[] dp = new int[s.length + 1];

        dp[0] = 0;
        dp[1] = s[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], s[i-1] + dp[i-2]);
        }

        return dp[dp.length - 1];
    }

    static int findMaxProfitTReconstrct(int[] s) {
        int[] dp = new int[s.length + 1];

        dp[0] = 0;
        dp[1] = s[0];
        boolean[] pick = new boolean[s.length + 1];
        pick[1] = true;

        for (int i = 2; i < dp.length; i++) {
            if(s[i-1] + dp[i-2] >  dp[i-1]) {
                pick[i] = true;
                dp[i] = s[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1];
            }
        }

        System.out.println(Arrays.toString(pick));
        System.out.println(Arrays.toString(dp));

        int i = pick.length-1;
        System.out.println("Houses to be robbed");
        while(i > 0) {
            if(pick[i]) {
                System.out.println(i);
                i = i-2;
            } else {
                i = i-1;
            }
        }

        System.out.println("");
        return dp[dp.length - 1];
    }
}