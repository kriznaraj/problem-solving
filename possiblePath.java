public class possiblePath {
    public static void main(String[] args) {
        System.out.println(doesPathExist(197, 9610, 1051, 6877));
    }

    static boolean doesPathExist(int a, int b, int x, int y) {
        if(a == x && b == y) {
            return true;
        }
        boolean res = false;
        if(a+b <= x) {
            res = res || doesPathExist(a+b, b, x, y);
        } else if(a-b >= x) {
            res = res || doesPathExist(a-b, b, x, y);
        } else if(a+b <= y) {
            res = res || doesPathExist(a, b + a, x, y);
        } else if(b-a >= y) {
            res = res || doesPathExist(a, b - a, x, y);
        }
        return res;
    }
}