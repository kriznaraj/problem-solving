
public class nqueen {
    static int c = 0;
    public static void main(String[] args) {
        int n = 100;
        boolean[][] board = new boolean[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = false;
            }
        }
        findQueen(board, n - 1);
    }

    static void findQueen(boolean[][] b, int q) {
        if (q == -1) {
            printBoard(b);
            c++;
            System.out.println("-------------------" + c);
            return;
        }

        for (int i = 0; i < b.length; i++) {
            if (can(b, q, i)) {
                b[q][i] = true;
                findQueen(b, q - 1);
                b[q][i] = false;
            }
        }
    }

    static boolean can(boolean[][] b, int q, int i) {
        for (int k = 0; k < b.length; k++) {
            if (b[k][i]) {
                return false;
            }

            if (check(b, q - k, i - k) || check(b, q + k, i + k) || check(b, q - k, i + k) || check(b, q + k, i - k)) {
                return false;
            }
        }
        return true;
    }

    static boolean check(boolean[][] b, int r, int i) {
        if (r >= 0 && r < b.length && i >= 0 && i < b.length) {
            return b[r][i];
        }
        return false;
    }

    static void printBoard(boolean[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (b[i][j]) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" o ");
                }
            }
            System.out.println("");
        }
    }

}