package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class solution {

    private boolean add;

    public List<Integer> getPaskalRow(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        Arrays.fill(row, 0);
        row[0] = 1;

        for (int i = 1; i < row.length; i++) {
            for (int j = i; j > 0; j--) {
                row[j] = row[j - 1] + row[j];
            }
        }
        return Arrays.asList(row);
    }

    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        int s = 0;
        int n = 1;
        for (int i = 2; i <= N; i++) {
            int t = s + n;
            s = n;
            n = t;
        }
        return n;
    }

    public int uniquePathsIII(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return util(grid, i, j);
                }
            }
        }
        return 0;
    }

    int util(int[][] grid, int i, int j) {
        if (grid[i][j] == 2 && check(grid)) {
            return 1;
        }
        int t = grid[i][j];
        grid[i][j] = -1;

        int left = 0;
        if (j - 1 >= 0 && grid[i][j - 1] != -1) {
            left = util(grid, i, j - 1);
        }

        int right = 0;
        if (j + 1 < grid[0].length && grid[i][j + 1] != -1) {
            right = util(grid, i, j + 1);
        }

        int top = 0;
        if (i - 1 >= 0 && grid[i - 1][j] != -1) {
            top = util(grid, i - 1, j);
        }

        int bottom = 0;
        if (i + 1 < grid.length && grid[i + 1][j] != -1) {
            bottom = util(grid, i + 1, j);
        }
        grid[i][j] = t;

        return left + right + top + bottom;
    }

    private boolean check(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> route = new TreeMap<>();
        for (int[] trip : trips) {
            route.put(trip[1], route.getOrDefault(trip[1], 0) + trip[0]);
            route.put(trip[2], route.getOrDefault(trip[2], 0) - trip[0]);
        }

        int c = 0;
        for (int t : route.keySet()) {
            c += route.get(t);
            if (c > capacity) {
                return false;
            }
        }
        return true;
    }

    public String reorderSpaces(String text) {
        int w = 0;
        List<String> words = new ArrayList<>();
        int l = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (i > l + 1) {
                    words.add(text.substring(l + 1, i));
                }
                w++;
                l = i;
            }
        }
        if (l + 1 < text.length()) {
            words.add(text.substring(l + 1, text.length()));
        }
        if (words.size() == 0) {
            return text;
        } else if (words.size() == 1) {
            String sp = "";
            for (int i = 0; i < w; i++) {
                sp += " ";
            }
            return words.get(0) + sp;
        }
        int s = w / (words.size() - 1);
        int d = w % (words.size() - 1);
        String space = "";
        for (int i = 0; i < s; i++) {
            space += " ";
        }
        String tail = "";
        for (int i = 0; i < d; i++) {
            tail += " ";
        }
        StringBuffer b = new StringBuffer();
        int i = 0;
        while (i < words.size() - 1) {
            b.append(words.get(i)).append(space);
            i++;
        }
        if (i < words.size()) {
            b.append(words.get(i));
        }
        b.append(tail);
        return b.toString();
    }

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (Integer k : count.keySet()) {
            if (count.get(k) > nums.length / 3) {
                res.add(k);
            }
        }
        return res;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int s = 0;
        int remain = 0;
        for (int i = 0; i < gas.length; i++) {
            var d = gas[i] - cost[i];
            if (remain + d < 0) {
                remain = 0;
                start = i + 1;
            } else {
                remain += d;
            }
            s += d;
        }
        if (s >= 0) {
            return start;
        }
        return -1;
    }

    public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for (Character c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (Character c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) {
                return c;
            }
        }
        return '\0';
    }

    public String largestNumber(int[] nums) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (int n : nums) {
            String d = String.valueOf(n);
            Integer k = base(n);
            if (!map.containsKey(k)) {
                map.put(k, new ArrayList<>());
            }
            map.get(k).add(d);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (map.containsKey(i)) {
                Collections.sort(map.get(i), (p, q) -> {
                    return (q + p).compareTo(p + q);
                });
                for (String n : map.get(i)) {
                    sb.append(n);
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }

    private int base(int n) {
        while (n > 9) {
            n /= 10;
        }
        return n;
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int h = 0;
        int n = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] < n) {
                h = h - (n - timeSeries[i]);
            }
            n = timeSeries[i] + duration;
            h += duration;
        }
        return h;
    }

    public int maxUniqueSplit(String s) {
        Set<String> res = new HashSet<>();
        // findCombination2(s, new HashSet<>(), res, 0);
        split(s, 0, res);
        return max;
    }

    int max = 0;

    void split(String str, int s, Set<String> res) {
        if (s == str.length()) {
            max = Math.max(res.size(), max);
            return;
        }

        if (res.size() + (str.length() - s) <= max) {
            return;
        }

        for (int i = s + 1; i <= str.length(); i++) {
            var w = str.substring(s, i);
            if (!res.contains(w)) {
                res.add(w);
                split(str, i, res);
                res.remove(w);
            }
        }
    }

    public int minOperations(String[] logs) {
        int level = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                level--;
                if (level < 0) {
                    level = 0;
                }
            } else if (log.equals("./")) {

            } else {
                level++;
            }
        }
        return level;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (4 * boardingCost <= runningCost) {
            return -1;
        }

        int remainingCustomers = 0;
        for (int i = 0; i < customers.length; i++) {
            customers[i] = customers[i] + remainingCustomers;
            remainingCustomers = customers[i] - Math.min(customers[i], 4);
        }

        return customers.length + findRotations(remainingCustomers, boardingCost, runningCost);
    }

    public int findRotations(int ramainingCustomers, int boardingCost, int runningCost) {
        int rotations = ramainingCustomers / 4;
        int r = ramainingCustomers % 4;

        int cost = (ramainingCustomers - r) * boardingCost - (rotations * runningCost);

        for (int i = ramainingCustomers - r + 1; i <= ramainingCustomers; i++) {
            int t = i * boardingCost - ((rotations + 1) * runningCost);
            if (t > cost) {
                return rotations + 1;
            }
        }
        return rotations;
    }

    class Edge {
        public String e;
        public Double v;

        public Edge(String e, Double v) {
            this.e = e;
            this.v = v;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> g = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            addEdge(g, eq.get(0), eq.get(1), values[i]);
            addEdge(g, eq.get(1), eq.get(0), 1 / values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            res[i] = dfs(g, new HashSet<>(), q.get(0), q.get(1));
        }

        return res;
    }

    private double dfs(Map<String, List<Edge>> g, Set<String> v, String s, String e) {
        if (v.contains(s)) {
            return -1;
        }
        if (!g.containsKey(s) || !g.containsKey(e)) {
            return -1;
        }
        if (s.equals(e)) {
            return 1;
        }
        v.add(s);

        for (Edge edge : g.get(s)) {
            if (edge.e.equals(e)) {
                return edge.v;
            } else {
                double d = dfs(g, v, edge.e, e);
                if (d != -1) {
                    return edge.v * d;
                }
            }
        }
        return -1;
    }

    private void addEdge(Map<String, List<Edge>> g, String s, String e, double v) {
        if (!g.containsKey(s)) {
            g.put(s, new ArrayList<>());
        }
        g.get(s).add(new Edge(e, v));
    }

    public int kthGrammar(int n, int k) {
        return util(n-1, k-1) ? 1 : 0;
    }

    private boolean util(int n, int k) {
        if(n <= 1) {
            if(k == 0) {
                return false;
            } else {
                return true;
            }
        }
        int m = (int) Math.pow(2, n)/2;
        return k < m ?  util(n-1, k) : !util(n-1, k-m);
    }

    public static void main(String[] args) {
        var s = new solution();
        // var r = s.carPooling(new int[][] { { 2,1,5}, { 3,3,7 }, { 8,3,9 } }, 11);
        // var r = s.reorderSpaces(" this is a sentence ");
        // var r = s.majorityElement(new int[]{3,3,3,1,1,1,2,2,});
        // var r = s.canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{2,3,5,3,5});
        // var r = s.findTheDifference("abcd", "abcde");
        // var r = s.findPoisonedDuration(new int[] {1,4}, 2);
        // var r = s.largestNumber(new int[] {3,30,34,5,9});
        // var r = s.minOperations(new String[] {"d1/","d2/","./","d3/","../","d31/"});
        // var r = s.minOperationsMaxProfit(new int[] {5,5,5,5,5}, 4, 4);
        // List<List<String>> eqs = new ArrayList<>();
        // eqs.add(Arrays.asList("a", "b"));
        // eqs.add(Arrays.asList("b", "c"));

        // double[] val = new double[] {2.0, 3.0};

        // List<List<String>> queries = new ArrayList<>();
        // queries.add(Arrays.asList("a", "c"));
        // queries.add(Arrays.asList("b", "a"));
        // queries.add(Arrays.asList("a", "e"));
        // queries.add(Arrays.asList("a", "a"));
        // queries.add(Arrays.asList("x", "x"));

        // var r = s.calcEquation(eqs, val, queries);

        var r = s.kthGrammar(3, 3);
        System.out.println(r);
    }
}