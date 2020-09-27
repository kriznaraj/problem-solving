package leetcode.concurrent;

import java.util.concurrent.CountDownLatch;

public class countDownLatch {

    public void latch() {
        final CountDownLatch countdown = new CountDownLatch(1);
        for (int i = 0; i < 10; ++i) {
            Thread racecar = new Thread() {
                public void run() {
                    try {
                        countdown.await();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    } // all threads waiting
                    System.out.println("Vroom!");
                }
            };
            racecar.start();
        }
        System.out.println("Go");
        countdown.countDown(); 
    }

    public static void main(String[] args) {
        countDownLatch latch = new countDownLatch();
        latch.latch();
    }
}
