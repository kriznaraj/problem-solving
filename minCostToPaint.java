public class minCostToPaint {
    
    public static void main(String[] args) {
        //   c1 c2 c3
        // h1
        // h2
        // h3
        
        int[][] cost = {
            {17,2,17},
            {16, 16, 5},
            {14,3,9}
        };

        int[][] mem = new int[cost.length][cost[0].length];

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cost.length; j++) {
            int c = findCost(cost, 0, j);
            if(c < min) {
                min = c;
            }
        }
        System.out.println(min);

        min = Integer.MAX_VALUE;
        for (int j = 0; j < cost.length; j++) {
            int c = findCostMemo(cost, 0, j, mem);
            if(c < min) {
                min = c;
            }
        }
        System.out.println(min);

        min = findCostT(cost);
        System.out.println(min);

        min = findCostTReconstruct(cost);
        System.out.println(min);
        
    }

    //O(2^n)
    static int findCost(int[][] cost, int h, int i) {

        if(h == cost.length) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cost.length; j++) {
            if(j == i) {
                continue;
            }

            int c = cost[h][i] + findCost(cost, h+1, j);
            if(c < min) {
                min = c;
            }
        }
        return min;
    }

    //O(n)
    static int findCostMemo(int[][] cost, int h, int i, int[][] memo) {

        if(h == cost.length) {
            return 0;
        }

        if(memo[h][i] != 0) {
            return memo[h][i];
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < cost.length; j++) {
            if(j == i) {
                continue;
            }

            int c = cost[h][i] + findCost(cost, h+1, j);
            if(c < min) {
                min = c;
            }
        }
        memo[h][i] = min;
        return min;
    }

    //O(n)
    static int findCostT(int[][] cost) {

        int[][] t = new int[cost.length][cost[0].length];

        for (int j = 0; j < t.length; j++) {
            for (int k = 0; k < t[j].length; k++) {
                if (j == 0) {
                    t[j][k] = cost[j][k];
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (int k2 = 0; k2 < t.length; k2++) {
                    if(k == k2) {
                        continue;
                    }
                    int c = cost[j][k] + t[j-1][k2];
                    if(c < min) {
                        min = c;
                    }
                }
                t[j][k] = min;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < t [t.length -1].length; j++) {
            if(t[t.length-1][j] < min) {
                min = t[t.length-1][j];
            }
        }
        return min;
    }

    //O(n)
    static int findCostTReconstruct(int[][] cost) {

        int[][] t = new int[cost.length][cost[0].length];
        int[][] color = new int[cost.length][cost[0].length];

        for (int j = 0; j < t.length; j++) {
            for (int k = 0; k < t[j].length; k++) {
                if (j == 0) {
                    t[j][k] = cost[j][k];
                    continue;
                }

                int min = Integer.MAX_VALUE;
                int clr = -1;
                for (int k2 = 0; k2 < t.length; k2++) {
                    if(k == k2) {
                        continue;
                    }
                    int c = cost[j][k] + t[j-1][k2];
                    if(c < min) {
                        min = c;
                        clr = k2;
                    }
                }
                t[j][k] = min;
                color[j][k] = clr;
            }
        }

        int min = Integer.MAX_VALUE;
        int c = -1;
        for (int j = 0; j < t [t.length -1].length; j++) {
            if(t[t.length-1][j] < min) {
                min = t[t.length-1][j];
                c = j;
            }
        }


        System.out.println("Colors");
        System.out.println(c);
        for (int i = t.length - 1; i > 0; i--) {
            c = color[i][c];
            System.out.println(c);
        }

        return min;
    }
}