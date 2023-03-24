package com.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @param <H> 参数
 * @param <Y>返回值
 */
public abstract class MultiThread<H, Y> {
    private final ExecutorService exec; //线程池
    private final BlockingQueue<Future<List<Y>>> queue = new LinkedBlockingQueue<>();
    private final CountDownLatch startLock = new CountDownLatch(1); //启动门，当所有线程就绪时调用countDown
    private final CountDownLatch endLock; //结束门
    private final List<H> listData;//被处理的数据


    public MultiThread(List<H> list){
        if(list!=null&&list.size()>0){
            this.listData = list;
            exec = Executors.newFixedThreadPool(5); //创建线程池，线程池共有nThread个线程
            endLock = new CountDownLatch(list.size());  //设置结束门计数器，当一个线程结束时调用countDown
        }else{
            listData = null;
            exec = null;
            endLock = null;
        }
    }


    /**
     *
     * @return 获取每个线程处理结束的数组
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<Y> getResult() throws InterruptedException, ExecutionException{
        List<Y> resultList = new ArrayList<>();
        if(listData!=null&&listData.size()>0){
            int nThread = listData.size(); //线程数量
            for(int i = 0; i < nThread; i++){
                H data = listData.get(i);
                Future<List<Y>> future = exec.submit(new Task(i,data){
                    @Override
                    public List<Y> execute(int currentThread,H data) {
                        return outExecute(currentThread,data);
                    }
                }); //将任务提交到线程池
                queue.add(future); //将Future实例添加至队列
            }
            startLock.countDown(); //所有任务添加完毕，启动门计数器减1，这时计数器为0，所有添加的任务开始执行
            endLock.await(); //主线程阻塞，直到所有线程执行完成
            for(Future<List<Y>> future : queue) {
                resultList.addAll(future.get());
            }
            exec.shutdown(); //关闭线程池
        }
        return resultList;
    }
    /**
     * 每一个线程执行的功能，需要调用者来实现
     * @param currentThread 线程号
     * @param data 每个线程被处理的数据
     * @return T返回对象
     */
    public abstract List<Y> outExecute(int currentThread,H data);

    /**
     * 线程类
     */
    private abstract class Task implements Callable<List<Y>>{
        private int currentThread;//当前线程号
        private H data;
        public Task(int currentThread,H data){
            this.currentThread=currentThread;
            this.data=data;
        }
        @Override
        public List<Y> call() throws Exception {
            startLock.await(); //线程启动后调用await，当前线程阻塞，只有启动门计数器为0时当前线程才会往下执行
            List<Y> t = null;
            try{
                t = execute(currentThread,data);
            }finally{
                endLock.countDown(); //线程执行完毕，结束门计数器减1
            }
            return t;
        }

        /**
         * 每一个线程执行的功能
         * @param currentThread 线程号
         * @param data 每个线程被处理的数据
         * @return T返回对象
         */
        public abstract List<Y> execute(int currentThread,H data);
    }

}
