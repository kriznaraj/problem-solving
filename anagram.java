import java.util.ArrayList;
import java.util.Arrays;

public class anagram {

    public static void main(String[] args) {
        char[] input = "GOD".toCharArray();
        Arrays.sort(input);
        findAnagram(input, new ArrayList<>(), new boolean[] {false, false, false});
    }

    static void findAnagram(char[] input, ArrayList<Character> partial, boolean[] used) {

        //if partial is valid -> process partial
        if(partial.size() == input.length) {
            System.out.println(Arrays.toString(partial.toArray()));
        }

        for (int i = 0; i < used.length; i++) { // for each candidates
            if(!used[i] && !(i > 0 && input[i] == input[i-1] && !used[i-1])) {
                //add candidate
                used[i] = true;
                partial.add(input[i]);

                //do
                findAnagram(input, partial, used);

                //remove candidate
                partial.remove(partial.size() - 1);
                used[i] = false;
            }
        }
    }

}