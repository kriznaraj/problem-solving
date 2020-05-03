import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class wordBrake {
    public static void main(String[] args) {
        String input = "catsanddog";
        String remaining = input;
        Set<String> words = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

        findWordBrake("catsanddog", new ArrayList<>(), remaining, words);
    }

    static void findWordBrake(String input, ArrayList<String> partial, String remaining, Set<String> words) {

        if(remaining.length() == 0) {
            System.out.println(Arrays.toString(partial.toArray()));
        }

        for (String word : words) {
            if(remaining.startsWith(word)) {
                partial.add(word);
                remaining = remaining.substring(word.length());

                findWordBrake(input, partial, remaining, words);

                partial.remove(word);
                remaining = word + remaining;
            }
        }
    }
}   