package com.demoPack.lock;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * volatile测试
 */
public class MainTest {
    private static volatile int count;

    @Test
    public void testVolatile() throws InterruptedException {
        Thread1[] thread1s = new Thread1[100];
        for (int i = 0; i < 100; i++){
            thread1s[i] = new Thread1();
            thread1s[i].start();
        }

        for (int j = 0; j < 100; j++){
            thread1s[j].join();
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        System.out.println(count);
    }
    //每个线程随机自增 count
    private class Thread1 extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}

