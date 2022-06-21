package com.demoPack.lock;

public class ExampleLock {

    // 可能发生线程安全问题的共享变量
    private static long count = 0;

    // 两个线程并发对 count++
    public static void main(String[] args) throws Exception {
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()-> add());
        Thread th2 = new Thread(()-> add());
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        // 这里应该是 20000 就对了，说明锁生效了
        System.out.println(count);
    }

    // 我画了一上午写出来的锁，哈哈
    private static ExampleLock exampleLock = new ExampleLock();

    // 循环 count++，进行 10000 次
    private static void add() {
//        exampleLock.lock();
//        for (int i = 0; i < 10000; i++) {
//            count++;
//        }
//        add2();
//        // 没啥异常，我就直接释放锁了
//        exampleLock.unlock();
    }
    
}
