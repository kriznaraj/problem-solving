package leetcode;

import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> stack;
    private int p;

    public ProductOfNumbers() {
        stack = new ArrayList<>();
        p = 1;
    }
    
    public void add(int num) {
        if(num == 0) {
            stack.clear();
            p = 1;
        } else {
            p *=num;
            stack.add(p);
        }
    }
    
    public int getProduct(int k) {
        if(k > stack.size()) {
            return 0;
        } else if(k == stack.size()){
            return p;
        } else {
            return p / stack.get(stack.size() - k - 1);
        }



        // if(next - k -1 < lstZero) {
        //     return 0;
        // } else if (next - k -1 == lstZero) {
        //     return product;
        // } else {
        //     return product / stack[next-k-1];
        // }
    }

    public static void main(String[] args) {
        ProductOfNumbers pon = new ProductOfNumbers();
        pon.add(3);
        pon.add(0);
        pon.add(2);
        pon.add(5);
        pon.add(4);
        System.out.println(pon.getProduct(2));
        System.out.println(pon.getProduct(3));
        System.out.println(pon.getProduct(4));
        pon.add(8);
        System.out.println(pon.getProduct(2));
    }
}