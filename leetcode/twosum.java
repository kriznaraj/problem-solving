package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


class Leetcode {

	public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                break;
            }
            map.put(target - nums[i], i);
        }
        return new int[] {map.get(target - nums[i]), i};
    }

    public List<List<Integer>> threeSum(int[] nums) {

        if(nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length -2; i++) {
            
            int j = i+1;
            int k = nums.length-1;
            while(j<k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                }
                else if (sum > 0) k--;
                else if (sum < 0) j++;
            }
        }

        return new ArrayList<>(set);
    }

    public int threeSumClosest(int[] nums, int t) {

        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int r = 0;
        for (int i = 0; i < nums.length -2; i++) {
            int j = i+1;
            int k = nums.length-1;
            
            while(j<k) {
                int sum = nums[i] + nums[j] + nums[k];
                int d = sum - t;
                if(Math.abs(d) < diff) {
                    diff = Math.abs(sum - t);
                    r = sum;
                }
                if (d == 0) {
                    return t;
                }
                else if (d > 0) k--;
                else if (d < 0) j++;
            }
        }
        return r;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> res = new HashSet<>();
        Set<String> seen = new HashSet<>();
        char[] a = s.toCharArray();
        for (int i = 0; i + 10 <= a.length; i++) {
            String t = s.substring(i, i+10);
            if(!seen.add(t)) {
                res.add(t);
            }
        }
        return new ArrayList<>(res);
    }

    public boolean canReach(int[] arr, int s) {
        
        return canReachUtil(arr, s, new HashSet<Integer>());
    }
    
    public boolean canReachUtil(int[] arr, int s, Set<Integer> set) {
        
        if(s < 0 || s >= arr.length || set.contains(s)) {
            return false;
        }
        if(arr[s] == 0) {
            return true;
        }
        set.add(s);
        return canReachUtil(arr, s-arr[s], set) || canReachUtil(arr, s+arr[s], set);
    }

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int d = minDistance(0, 0, target);
        for (int i = 0; i < ghosts.length; i++) {
            int gd = minDistance(ghosts[i][0], ghosts[i][1], target);
            if(gd <= d) {
                return false;
            }
        }
        return true;
    }

    private int minDistance(int x, int y, int[] t) {
        return Math.abs(x-t[0]) + Math.abs(y - t[1]);
    }

    public List<Integer> getAllElements(TreeNode c1, TreeNode c2) {
        
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        List<Integer> res = new ArrayList<>();

        while(c1 != null || c2 != null || !s1.isEmpty() || !s2.isEmpty()) {
            while(c1 != null) {
                s1.add(c1.left);
                c1 = c1.left;
            }
            while(c2 != null) {
                s2.add(c2.left);
                c2 = c2.left;
            }

            if(s1.peek().val < s2.peek().val) {
                res.add(s1.peek().val);
                c1 = s1.pop().right;
            } else {
                res.add(s2.peek().val);
                c1 = s2.pop().right;
            }
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
 

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if(pattern.length() != words.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if(map.containsKey(c) && !map.get(c).equals(words[i])) {
                return false;
            } else if(map2.containsKey(words[i]) && map2.get(words[i]) != c) {
                return false;
            } else if(map.containsKey(c)) {
                continue;
            }
            
            map.put(c, words[i]);
            map2.put(words[i], c);
        }
        return true;
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        String[] min, max;
        if(v1.length > v2.length) {
            max = v1;
            min = v2;
        } else {
            min = v1;
            max = v2;
        }

        for (int i = 0; i < min.length; i++) {
            int p = Integer.parseInt(v1[i]);
            int q = Integer.parseInt(v2[i]);
            if(p > q) {
                return 1;
            } else if(p < q) {
                return -1;
            }
        }
        for (int i = min.length; i < max.length; i++) {
            if(Integer.parseInt(max[i]) > 0) {
                return v1.length > v2.length ? 1 : -1;
            }
        }
        return 0;
    }

    public String getHint(String secret, String guess) {
        int bulls = 0;
        int[] smap = new int[10];
        int[] gmap = new int[10];
        for(int i = 0; i<secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                smap[secret.charAt(i) - '0'] = smap[secret.charAt(i) - '0'] + 1;
                gmap[guess.charAt(i) - '0'] = gmap[guess.charAt(i) - '0'] + 1;
            }
        }

        int cows = 0;
        for (int i = 0; i < 10; i++) {
            if(smap[i] <= gmap[i]) {
                cows +=smap[i];
            } else {
                cows +=gmap[i];
            }
        }
        return String.format("%dA%dB", bulls, cows);
        
    }

    public int maxProduct(int[] nums) {
        int res = nums[0];
        int max = res;
        int min = res;
        for (int i = 1; i < nums.length; i++) {

            int t = max;
            max = Math.max(nums[i], Math.max(max*nums[i], min*nums[i]));
            min = Math.min(nums[i], Math.min(t*nums[i], min*nums[i]));
            
            if(max >res) {
                res = max;
            }
        }
        return res;
    }

    public int[][] insert(int[][] intervals, int[] n) {

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            
            if(n == null || intervals[i][1] < n[0]) {
                res.add(intervals[i]);
            } else if(intervals[i][0] > n[1]) {
                res.add(n);
                res.add(intervals[i]);
                n = null;
            } else {
                n[0] = Math.min(n[0], intervals[i][0]);
                n[1] = Math.max(n[1], intervals[i][1]);
            }
        }
        if(n != null) {
            res.add(n);
        }
        for (int[] i : res) {
            System.out.print(Arrays.toString(i));
        }
        System.out.println("");
        return res.toArray(new int[0][]);
    }

    public int rob(int[] nums) {
        if(nums == null || nums.length == 0 ) {
            return 0;
        }
        
        int i = 0;
        int j = nums[0];
        int m = j;
        for (int k = 1; k < nums.length; k++) {
            int t = Math.max(i + nums[k], j);
            if(t > m) {
                m = t;
            }
            i = j;
            j = t;
        }
        return m;
    }

    public List<Integer> sequentialDigits(int low, int high) {

        int s = low;
        int d = 1;
        while(s>=10) {
            s=s/10;
            d++;
        }
        int e = 1;
        int t = high;
        while(t>=10) {
            t=t/10;
            e++;
        }

        //sequentialDigits(low, high, d, e, s+"", new ArrayList<>());
        return new ArrayList<>();
    }

    //9 -> 12 -> 23 -> 34
    //12
    //12
    //12 -> 123
    //89 -> 123
    //90 + 100 
    //1*100 + 2*10
    //123 -> 134

    //200
    //1*200 + 

    public void dfs(int low, int high) {
        var res = new ArrayList<Integer>();
        var q = new ArrayDeque<Integer>();
        for (int i = 1; i < 10; i++) {
            q.add(i);
        }

        while(!q.isEmpty()) {
            var c = q.poll();
            if(c >= low && c <= high) {
                System.out.println(c);
                res.add(c);
            }
            var d = c%10;
            if(d != 9) {
                var n = c*10 + d+1;
                if(n < high) {
                    // System.out.println(n);
                    q.add(n);
                }
            }
        }
    }

    void helper(char[] s, char c, int i) {
        if(i+1 < s.length) {
            helper(s, s[i+1], i + 1);
        }
        s[s.length - i - 1] = c;
    }

    void helper(char[] s, int l, int r) {
        if(l<=r) {
            char t = s[r];
            s[r] = s[l];
            s[l] = t;
            helper(s, l + 1, r -1);
        }
    }

    //2345
    public static void main(String[] args) {

        Leetcode solution = new Leetcode();
        // solution.dfs(2000, 50000);
        char[] c = "sindhu".toCharArray();
        // solution.helper(c, 'q', 0);
        // System.out.println(c);
        solution.helper(c, 0, c.length -1);
        System.out.println(c);

        // System.out.println(solution.wordPattern("aaa", "aa aa aa aa"));
        // System.out.println(solution.compareVersion("0.1.0", "1.1"));
        // System.out.println(solution.getHint("1207", "7210"));
        //2,3,-2,4,-6,1,1,1,1,-1,-1,-1,-1,5-1,8,0,999
        //0,2
        //3,-1,4
        //2,-5,-2,-4,3
        // System.out.println(solution.maxProduct(new int[] {2,3,-2,4,-6,1,1,1,1,-1,-1,-1,-1,5,-1,8,0,999}));
        // solution.insert(new int[][] {{1,3}, {6,9}}, new int[] {2,5});

        // solution.insert(new int[][] {{1,2}, {6,9}}, new int[] {3,5});

        // solution.insert(new int[][] {{1,2}, {3,5}, {6,7}, {8,10}, {12,16}}, new int[] {4,8});

        // System.out.println( solution.rob(new int[] {2,7,9,3,1}));
        
    }
}
