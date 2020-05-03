
public class binomialCoefficient {
    public static void main(String[] args) {
        int n = 6;
        int k = 4;
        System.out.println(biCoEfM(n, k, new int[n + 1][k + 1]));
        System.out.println(biCoEfT(n, k));
    }

    static int biCoEfM(int n, int k, int[][] mem) {

        if(k == 0) {
            return 1;
        }
        if(n == k) {
            return 1;
        }

        if(mem[n][k] != 0) {
            return mem[n][k];
        }
        int r = biCoEfM(n-1, k-1, mem) + biCoEfM(n-1, k, mem);
        mem[n][k] = r;
        return r;
    }

    static int biCoEfT(int n, int k) {

        int t[][] = new int[n+1][k+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if(i == 0 || j == 0) {
                    t[i][j] = 1;
                    continue;
                }

                if(i == j) {
                    t[i][j] = 1;
                    continue;
                }

                t[i][j] = t[i-1][j-1] + t[i-1][j];
            }
        }

        return t[n][k];

    }
}   