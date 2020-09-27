import java.util.Arrays;

public class regExpMatch {
    public static void main(String[] args) {

        String s = "ABBBAC";
        String r = ".*A*";

        System.out.println(doesMatch(s, r, s.length() - 1, r.length() - 1));
        System.out.println(doesMatch(s, r));
    }

    static boolean doesMatch(String s, String r, int i, int j) {
        if (i == -1 && j == -1) {
            return true;
        } else if (i == -1) {
            return false;
        } else if (j == -1) {
            return false;
        }

        if (s.charAt(i) == r.charAt(j)) {
            return doesMatch(s, r, i - 1, j - 1);
        } else if (r.charAt(j) == '.') {
            return doesMatch(s, r, i - 1, j - 1);
        } else if (r.charAt(j) == '*') {
            return doesMatch(s, r, i - 1, j) || doesMatch(s, r, i - 1, j - 1);
        } else {
            return false;
        }
    }

    static boolean doesMatch(String s, String r) {
        boolean[][] dp = new boolean[s.length() + 1][r.length() + 1];
        char[][] cdp = new char[s.length() + 1][r.length() + 1];

        System.out.println("[ ][ ]" + Arrays.toString(r.toCharArray()));
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < r.length() + 1; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if(i == 0 || j == 0) {
                    dp[i][j] = false;
                } else if(s.charAt(i-1) == r.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if(r.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(r.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j-1] || dp[i-1][j];
                } else {
                    dp[i][j] = false;
                }
                cdp[i][j] = dp[i][j] ? 'T' : 'F';
            }
            if (i > 0) {
                System.out.println("[" + s.charAt(i - 1) + "]" + Arrays.toString(cdp[i]));
            } else {
                System.out.println("[ ]" + Arrays.toString(cdp[i]));
            }
        }
        return dp[s.length()][r.length()];
    }
    // [ ][ ][., *, A, *]
    // [ ][T, F, F, F, F]
    // [A][F, T, F, F, F]
    // [B][F, F, T, F, F]
    // [B][F, F, T, F, F]
    // [B][F, F, T, F, F]
    // [A][F, F, T, T, F]
    // [C][F, F, T, F, T]
}