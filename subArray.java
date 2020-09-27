import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class subArray {
    public static void main(String[] args) {
        // int arr[] = {5, 2, -2, -20, 10};
        // int n = arr.length;
        // int sum = -10;

        // int arr[] = { 1, 2, 3, 4, 6 };
        // int n = arr.length;

        // subArraySum(arr, n, sum);


        Vector<String> arr; 
        arr = new Vector<>(); 
        arr.add("54"); 
        arr.add("546"); 
        arr.add("548"); 
        arr.add("60"); 
        printLargest(arr); 

        // System.out.println(getMissingNo(arr, n));
    }

    static void subArraySum(int arr[], int n, int sum) {
        // create an empty map
        Map<Integer, Integer> map = new HashMap<>();

        // Maintains sum of elements so far
        int curr_sum = 0;

        for (int i = 0; i < n; i++) {
            // add current element to curr_sum
            curr_sum = curr_sum + arr[i];

            // if curr_sum is equal to target sum
            // we found a subarray starting from index 0
            // and ending at index i
            if (curr_sum == sum) {
                // cout << "Sum found between indexes "
                // << 0 << " to " << i << endl;
                System.out.println(0 + " " + i);
                return;
            }

            // If curr_sum - sum already exists in map
            // we have found a subarray with target sum
            if (map.containsKey(curr_sum - sum)) {
                // cout << "Sum found between indexes "
                // << map[curr_sum - sum] + 1
                // << " to " << i << endl;
                System.out.println((map.get(curr_sum - sum) + 1) + " " + i);
                return;
            }

            map.put(curr_sum, i);
        }

    }

    static int getMissingNo(int a[], int n) 
    { 
        // For xor of all the elements in array 
        int x1 = a[0]; 
    
        // For xor of all the elements from 1 to n+1 
        int x2 = 1; 
    
        for (int i = 1; i < n; i++) 
            x1 = x1 ^ a[i]; 
    
        for (int i = 2; i <= n + 1; i++) 
            x2 = x2 ^ i; 

        // Arrays.binarySearch(a, key)
    
        return (x1 ^ x2); 
    }

    static void printLargest(List<String> arr) {

        Collections.sort(arr, new Comparator<String>() {

            // A comparison function which is used by
            // sort() in printLargest()
            @Override
            public int compare(String X, String Y) {

                // first append Y at the end of X
                String XY = X + Y;

                // then append X at the end of Y
                String YX = Y + X;

                // Now see which of the two formed numbers
                // is greater
                return XY.compareTo(YX) > 0 ? -1 : 1;
            }
        });

        Iterator it = arr.iterator();

        while (it.hasNext())
            System.out.print(it.next());

    }
}