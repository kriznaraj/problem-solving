import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        //int[] input = {-1, 2, 0, 1, 0, 1};
        int[] input_1 = {-1, 1, 0, 1, 0, 1};
        int[] input_2 = {1,2,3,7,5};
		
		//System.out.println(obj.subarraySum(input, 2));
        // System.out.println(subarraySum(input_1, 2));
        System.out.println(subarraySum(input_2, 12));


        int[] arr = {10, 2, -2, -20, 10}; 
        int n = arr.length; 
        int sum = -10; 
        subArraySum(arr, n, sum); 
    }

    public static int subarraySum(int[] nums, int k) {
		int resultCount = 0;
		Map<Integer, Integer> sumCountMap = new HashMap<>();
		sumCountMap.put(0, 1);
		
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			int difference = sum - k;
			if (sumCountMap.containsKey(difference)) {
				resultCount = resultCount + sumCountMap.get(difference);
			}
			sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
		}
        
		return resultCount;
    }

    public static void subArraySum(int[] arr, int n, int sum) { 
        //cur_sum to keep track of cummulative sum till that point 
        int cur_sum = 0; 
        int start = 0; 
        int end = -1; 
        HashMap<Integer, Integer> hashMap = new HashMap<>(); 
  
        for (int i = 0; i < n; i++) { 
            cur_sum = cur_sum + arr[i]; 
            //check whether cur_sum - sum = 0, if 0 it means 
            //the sub array is starting from index 0- so stop 
            if (cur_sum - sum == 0) { 
                start = 0; 
                end = i; 
                break; 
            } 
            //if hashMap already has the value, means we already  
            // have subarray with the sum - so stop 
            if (hashMap.containsKey(cur_sum - sum)) { 
                start = hashMap.get(cur_sum - sum) + 1; 
                end = i; 
                break; 
            } 
            //if value is not present then add to hashmap 
            hashMap.put(cur_sum, i); 
  
        } 
        // if end is -1 : means we have reached end without the sum 
        if (end == -1) { 
            System.out.println("No subarray with given sum exists"); 
        } else { 
            System.out.println("Sum found between indexes " 
                            + start + " to " + end); 
        } 
  
    } 
}