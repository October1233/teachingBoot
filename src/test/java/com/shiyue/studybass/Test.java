package com.shiyue.studybass;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test{
//    public static void main(String[] args) {
//        /*
//        匿名内部类的方式
//        new Thread(new RunnableAndThread() {
//            @Override
//            public void run() {
//                System.out.println("nihao");
//            }
//        }).start();
//        System.out.println("你好");
//
//        */
//
//
//        //lambda的方式
//        new Thread(()-> {
//            for(int i = 1 ; i<100 ; i++){
//                System.out.println("It is a lambda function!");
//            }
//
//        }).start();
//
//    }
public static void main(String[] args) throws InterruptedException, ExecutionException {
    long start = System.currentTimeMillis();

    // 等凉菜
    Callable ca1 = new Callable(){

        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "凉菜准备完毕";
        }
    };
    FutureTask<String> ft1 = new FutureTask<String>(ca1);
    new Thread(ft1).start();

    // 等包子 -- 必须要等待返回的结果，所以要调用join方法
    Callable ca2 = new Callable(){

        @Override
        public Object call() throws Exception {
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "包子准备完毕";
        }
    };
    FutureTask<String> ft2 = new FutureTask<String>(ca2);
    new Thread(ft2).start();

    System.out.println(ft1.get());
    System.out.println(ft2.get());

    long end = System.currentTimeMillis();
    System.out.println("准备完毕时间："+(end-start));
}



}




