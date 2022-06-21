package com.demoPack.sycn;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class SynchronizedDemo3 {
    public synchronized void method1() {
        out.println("Method 1 start");
        try {
            out.println("Method 1 execute...");
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println("Method 1 end!");
    }

    public synchronized void method2() {
        out.println("Method 2 start");
        try {
            out.println("Method 2 execute...");
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println("Method 2 end!");
    }


    public static void main(String[] args) {
        final SynchronizedDemo3 instance1 = new SynchronizedDemo3();
        final SynchronizedDemo3 instance2 = new SynchronizedDemo3();
        new Thread(instance1::method1).start();
        new Thread(instance2::method2).start();
    }
    //结果分析：因为两个线程作用于不同的对象，获得的是不同的锁，所以互相并不影响

}
