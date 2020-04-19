import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class combination {
    public static void main(String[] args) {
        findCombination(new int[] {1,2,3,4}, new HashSet<>(), new ArrayList<>(), 3, 0);
        System.out.println("-------------");
        findCombination2(new int[] {1,2,3,4}, new HashSet<>(), 3, 0);
    }

    static void findCombination(int[] input, Set<Integer> set, List<Integer> partial, int k, int start) {

        if(set.size() == k) {
            System.out.println(Arrays.toString(set.toArray()));
            return;
        }

        for (int i = start; i < input.length; i++) {
            set.add(input[i]);
            partial.add(input[i]);
            findCombination(input, set, partial, k, i + 1);
            set.remove(input[i]);
            partial.remove(partial.size() - 1);
        }
    }

    //alternative approach - take current element or skip
    static void findCombination2(int[] input, Set<Integer> set, int k, int i) {

        if(set.size() == k) {
            System.out.println(Arrays.toString(set.toArray()));
            return;
        }

        if(i == input.length) {
            return;
        }

        set.add(input[i]);
        findCombination2(input, set, k, i + 1);
        set.remove(input[i]);
        findCombination2(input, set, k, i + 1);
    }
}