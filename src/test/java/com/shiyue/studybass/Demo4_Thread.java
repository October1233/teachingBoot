package com.shiyue.studybass;

class NewThread implements Runnable {
    Thread t;

    NewThread() {
        // 创建第二个新线程
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
        t.start(); // 开始线程
    }

    // 第二个线程入口
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                // 暂停线程
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }


    public static void main(String args[]) {
        new NewThread(); // 创建一个新线程
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }

}
public class Demo4_Thread {

    int tic =10;
    public void run() {
        while(true) {
            synchronized("") {
                if(tic>0)
                {
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("ticket"+tic--);
                }
            }
        }
    }

    public static void main(String[] args) {

        new Thread("线程1") {
            public void run() {
                System.out.println(this.getName() +": aaaaaa");
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("线程2") {
            public void run() {
                System.out.println(this.getName() +": bbbbb");
            }
        }.start();
    }

}


