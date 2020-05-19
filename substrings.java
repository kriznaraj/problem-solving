import java.util.Arrays;

public class substrings {
    public static void main(String[] args) {
        // System.out.println(findSubStrings("972698438521"));

        System.out.println(find("121"));
        // System.out.println(parseInt("972698438521"));
    }

    static int findSubStrings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                int p = parseInt(s.substring(j, i));
                sum = (sum + p) % 1000000007;
            }
            dp[i] = (dp[i-1] + sum) % 1000000007;
        }

        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }

    static int find(String s) {
        int l = s.length();
        int res = 0;
        int f = 1;
        for(int i = l-1; i >= 0; i--) {
            res = (res + (s.charAt(i) - '0')*f*(i+1)) % 1000000007;
            f = (f*10+1) % 1000000007;
        }
        return res;
    }

    static int parseInt(String s) {
        int l = s.length();
        int f = 1;
        int r = 0;
        while(l-- > 0) {
            int x = s.charAt(l) - '0';
            r = r + (x * f) % 1000000007;
            f*=10;
        }
        return r;
    }
}