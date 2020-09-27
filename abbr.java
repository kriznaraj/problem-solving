import java.util.Arrays;

public class abbr {
    // https://www.hackerrank.com/challenges/abbr/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
    public static void main(String[] args) {
        String a = "AbcDE";
        String b = "ABDE";
        System.out.println(couldAbbreviate(a, b));
    }

    static boolean couldAbbreviate(String a, String b) {
        boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];

        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0 && j > 0) {
                    dp[i][j] = false;
                } else if (i > 0 && j == 0) {
                    dp[i][j] = true;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (a.charAt(i - 1) >= 'a' && a.charAt(i - 1) <= 'z') {
                    if(b.charAt(j - 1) == 'A' + a.charAt(i - 1) - 'a') {
                        dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[a.length()][b.length()];
    }
}