import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class countWays {
    public static void main(String[] args) {
        String input = "pineapplepenapple";
        Set<String> words = new HashSet<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));

        // String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        // Set<String> words = new HashSet<>(Arrays.asList("a", "aa", "aaa", "aaaa"));

        // System.out.println(findCount(input, words, input.length() - 1));
        System.out.println(findCountT(input, words));
    }

    static int findCount(String s, Set<String> dict, int i) {
        if(i == -1) {
            return 1;
        }

        int sum = 0;
        for (int j = 0; j <= i; j++) {
            if(dict.contains(s.substring(i-j, i + 1))) {
                sum = sum + findCount(s, dict, i - (j + 1));
            }
        }
        return sum;
    }

    static int findCountT(String s, Set<String> dict) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                if(dict.contains(s.substring(i -j, i))) {
                    sum = sum + dp[i - j];
                }
            }
            dp[i] = sum;
        }

        System.out.println(Arrays.toString(dp));

        return dp[s.length()];
    }
}