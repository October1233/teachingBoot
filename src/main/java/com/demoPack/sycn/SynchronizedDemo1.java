package com.demoPack.sycn;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

public class SynchronizedDemo1 {
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

    public /*synchronized*/ void method2() {
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
        final SynchronizedDemo1 instance = new SynchronizedDemo1();

        new Thread(instance::method1).start();
        new Thread(instance::method2).start();

        Queue queue = new LinkedList();
    }
    //结果分析：当线程1还在执行时，线程2也执行了，所以当其他线程来访问非synchronized修饰的方法时是可以访问的

}
