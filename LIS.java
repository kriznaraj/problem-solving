import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 2, 3, 6, 10, 7,8 };
        int[] mem = new int[arr.length];

        System.out.println(findLis(arr, arr.length - 1));
        System.out.println(findLisM(arr, mem, arr.length - 1));
        System.out.println(findLisT(arr));
        System.out.println(findLisTReconstruct(arr));
    }

    static int findLis(int[] arr, int i) {

        if (i == 0) {
            return 1;
        }

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < i; j++) {
            int lis = findLis(arr, j);
            if (arr[j] < arr[i]) {
                lis = lis + 1;
            }

            if (lis > max) {
                max = lis;
            }
        }

        return max;
    }

    static int findLisM(int[] arr, int[] mem, int i) {

        if (i == 0) {
            return 1;
        }

        if (mem[i] != 0) {
            return mem[i];
        }

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < i; j++) {
            int lis = findLis(arr, j);
            if (arr[j] < arr[i]) {
                lis = lis + 1;
            }

            if (lis > max) {
                max = lis;
            }
        }
        mem[i] = max;
        return max;
    }

    static int findLisT(int[] arr) {
        int[] dp = new int[arr.length + 1];

        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                int lis = dp[j];
                if (arr[j - 1] < arr[i - 1]) {
                    lis = lis + 1;
                }

                if (lis > max) {
                    max = lis;
                }
            }
            dp[i] = max;
        }

        System.out.println(Arrays.toString(dp));
        return dp[arr.length];
    }

    static int findLisTReconstruct(int[] arr) {
        int[] dp = new int[arr.length + 1];
        int[] preList = new int[arr.length];

        dp[1] = 1;
        preList[0] = 0;

        for (int i = 2; i < dp.length; i++) {
            int max = Integer.MIN_VALUE;
            int pre = -1;
            for (int j = 1; j < i; j++) {
                int lis = dp[j];
                if (arr[j - 1] < arr[i - 1]) {
                    lis = lis + 1;
                    pre = j-1;
                }

                if (lis > max) {
                    max = lis;
                }
            }
            dp[i] = max;
            preList[i-1] = pre;
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(preList));
        return dp[arr.length];
    }
}