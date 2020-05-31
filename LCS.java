import java.util.Arrays;

public class LCS {
    public static void main(String[] args) {
        // String a = "ACHEFMGLP";
        // String b = "XYCEPQMLG";
        // String a = "YXAE";
        // String b = "XPAE";
        String a = "KRISHNARAJ";
        String b = "SINDHU";
        System.out.println(findLCS(a, b, a.length() - 1, b.length() - 1));
        System.out.println(findLCS(a, b));
    }

    static int findLCS(String a, String b, int i, int j) {

        if (i == -1 || j == -1) { // empty string
            return 0;
        }

        if (a.charAt(i) == b.charAt(j)) {
            return 1 + findLCS(a, b, i - 1, j - 1);
        } else {
            return Math.max(findLCS(a, b, i - 1, j), findLCS(a, b, i, j - 1));
        }
    }

    static int findLCS(String a, String b) {

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        System.out.println("[ ][ ]" + Arrays.toString(b.toCharArray()));

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            if (i > 0) {
                System.out.println("[" + a.charAt(i - 1) + "]" + Arrays.toString(dp[i]));
            } else {
                System.out.println("[ ]" + Arrays.toString(dp[i]));
            }

        }

        int i = a.length();
        int j = b.length();
        while (dp[i][j] > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                System.out.println("PICK => " + a.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}