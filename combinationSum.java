import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class combinationSum {
    public static void main(String[] args) {
        findCombinationSum2(new int[] { 1, 1, 2, 5, 6, 7, 10 }, new ArrayList<>(), 0, 8, 0);
        System.out.println("---------");
        findCombSum(new int[] {1, 1, 2, 5, 6, 7, 10 }, new ArrayList<>(), 0, 8, 0);
    }
    
    static void findCombSum(int[] input, List<Integer> partial, int sum, int k, int start) {
        if (sum == k) {
            System.out.println(Arrays.toString(partial.toArray()));
            return;
        }

        for (int i = start; i < input.length; i++) {
            int c = input[i];
            //to avoid duplicates
            if (sum + c > k || i > start && input[i] == input[i - 1]) {
                continue;
            } 

            partial.add(input[i]);
            findCombSum(input, partial, sum + c, k, i + 1);
            partial.remove(partial.size() - 1);
        }
    }

    static void findCombinationSum2(int[] input, List<Integer> partial, int sum, int k, int i) {
        if (sum == k) {
            System.out.println(Arrays.toString(partial.toArray()));
            return;
        }


        if (i == input.length) {
            return;
        }

        if (sum + input[i] <= k ) {
            partial.add(input[i]);
            findCombinationSum2(input, partial, sum + input[i], k, i + 1);
            partial.remove(partial.size() - 1);
            findCombinationSum2(input, partial, sum, k, i + 1);
        }
    }
}