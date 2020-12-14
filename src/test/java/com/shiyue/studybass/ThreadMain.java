package com.shiyue.studybass;

import com.shiyue.springboot.Car;
import com.shiyue.springboot.domain.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class ThreadMain {
    public static void main(String[] args) {

        ThreadC threadc = new ThreadC();
        FutureTask<User> faeature = new FutureTask<User>(threadc);
        new Thread(faeature).start();//注意启动方式，FutureTask将被作为Runnable被线程执行

        System.out.println("这是主线程；begin！");
        //注意细细体会这个，只有主线程get了，主线程才会继续往下执行
        try {
            System.out.println("得到的返回结果是："+faeature.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("这是主线程；end！");
    }
}