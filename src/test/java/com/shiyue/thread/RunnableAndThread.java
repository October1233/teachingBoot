package com.shiyue.thread;

import com.shiyue.springboot.repository.TestMapper;
import com.shiyue.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

public class RunnableAndThread {

    /**
     * Created by liwei on 16/7/19.
     * 使用匿名内部类的格式:
     * *        new 类名或者接口名() {
     *          重写方法;
     *      };
     *      本质：是该类或者接口的子类对象。
     */




        public static void main(String[] args) {
            // 继承 Thread 类来实现多线程
            new Thread("线程1"){
                @Override
                public void run(){
                    for (int x=0;x<100;x++){
                        System.out.println(Thread.currentThread().getName() + ":" + x);
                    }
                }
            }.start();

            System.out.println("------ 无聊的分割线 ------");
            // 实现 RunnableAndThread 接口
            new Thread(new Runnable() {

                @Autowired
                TestService testService;

                @Override
                public void run() {
                    for (int x=0;x<100;x++){
                        testService.ccdd();
                        System.out.println(Thread.currentThread().getName() + ":" + x);
                    }
                }
            },"线程2").start();

            // // TODO: 16/7/19 看不明白了
            // 更有难度的
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = 0; x < 100; x++) {
                        System.out.println("hello" + ":" + x);
                    }
                }
            }) {
                public void run() {
                    for (int x = 0; x < 100; x++) {
                        System.out.println("world" + ":" + x);
                    }
                }
            }.start();

        }
    }


