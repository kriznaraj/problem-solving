import java.util.Arrays;

public class editDistance {
    public static void main(String[] args) {

        String a = "GOAT";
        String b = "GET";

        System.out.println(findMinEditDistance(a.length() - 1, b.length() - 1, a, b));
        System.out.println(findMinEditDistance(a, b));

    }

    static int findMinEditDistance(int i, int j, String a, String b) {

        if (i == -1 && j == -1) {
            return 0;
        }

        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (a.charAt(i) == b.charAt(j)) {
            return findMinEditDistance(i - 1, j - 1, a, b);
        }

        int insert = 1 + findMinEditDistance(i, j - 1, a, b);
        int delete = 1 + findMinEditDistance(i - 1, j, a, b);
        int replace = 1 + findMinEditDistance(i - 1, j - 1, a, b);

        int min = Math.min(insert, delete);
        return Math.min(min, replace);
    }

    static int findMinEditDistance(String a, String b) {

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    int min = Math.min(insert, delete);
                    dp[i][j] = Math.min(min, replace);
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[a.length()][b.length()];
    }
}