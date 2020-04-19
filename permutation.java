import java.util.ArrayList;
import java.util.Arrays;

public class permutation {

    public static void main(String[] args) {
        findPermutation(new int[] {1,2,3, 4}, new ArrayList<>(), new boolean[] {false, false, false, false});
    }

    static void findPermutation(int[] input, ArrayList<Integer> partial, boolean[] used) {

        //if partial is valid -> process partial
        if(partial.size() == input.length) {
            System.out.println(Arrays.toString(partial.toArray()));
        }

        for (int i = 0; i < used.length; i++) { // for each candidates
            if(!used[i]) {
                //add candidate
                used[i] = true;
                partial.add(input[i]);

                //do
                findPermutation(input, partial, used);

                //remove candidate
                partial.remove(partial.size() - 1);
                used[i] = false;
            }
        }
    }
}