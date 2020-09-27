import java.util.HashMap;
import java.util.Map;

public class diagnolSum {

    class Node {
        int e;
        Node left;
        Node right;
    }
    
    public static void main(String[] args) {
        
    }

    static void dirver(Node root) {
        Map<Integer, Integer> ds = new HashMap<>();
        findDiagnolSum(root, 0, ds);

        for (Integer v : ds.values()) {
            System.out.println(v);
        }
    }

    static void findDiagnolSum(Node root, int d, Map<Integer, Integer> ds) {

        if(ds.containsKey(d)) {
            ds.put(d, ds.get(d) + root.e);
        } else {
            ds.put(d, root.e);
        }

        findDiagnolSum(root.left, d + 1, ds);
        findDiagnolSum(root.right, d, ds);
    }
}