import java.util.Arrays;

public class minCharRemovePalindrome {
    public static void main(String[] args) {
        String a = "KAZAYAKE";
        // String a = "KKK";
        // String a = "geeksforgeeks";
        System.out.println(findMinCharsToRemove(a, 0, a.length() - 1));
        System.out.println(findMinCharsToRemove(a));
    }

    static int findMinCharsToRemove(String a, int i, int j) {
        if (i > j) {
            return 0;
        }

        if (a.charAt(i) == a.charAt(j)) {
            return findMinCharsToRemove(a, i + 1, j - 1);
        } else {
            return 1 + Math.min(findMinCharsToRemove(a, i + 1, j), findMinCharsToRemove(a, i, j - 1));
        }
    }

    static int findMinCharsToRemove(String a) {
        int n = a.length();
        int[][] dp = new int[n][n];
        boolean[][] toTake = new boolean[n][n];

        for (int L = 0; L < n; L++) {
            for (int i = 0; i < n - L; i++) {
                int j = i + L;

                if (i == j) {
                    dp[j][j] = 0;
                    continue;
                }

                if (a.charAt(i) == a.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    toTake[i][j] = true;
                } else {

                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][n - 1];
    }
}