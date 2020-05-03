
public class fibonaci {
    public static void main(String[] args) {
        System.out.println(fibbM(10, new int[11]));
        System.out.println(fibbT(10));
    }

    static int fibbM(int n, int[] memo) {
        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        if(memo[n] != 0) {
            return memo[n];
        }

        int f = fibbM(n - 1, memo) + fibbM(n - 2, memo);
        memo[n] = f;
        return f;
    }

    static int fibbT(int n) {
        int[] t = new int[n+1];
        t[0] = 0;
        t[1] = 1;

        for (int i = 2; i <= n; i++) {
            t[i] = t[i-1] + t[i-2];
        }
        return t[n];
    }
    
}   