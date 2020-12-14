package com.shiyue.studybass;

public class Singleton {

    private static volatile Singleton instence = null;

    private Singleton() {}

    public static Singleton toInstence(){
        if (null == instence){
            synchronized (Singleton.class){
                if (null == instence){
                    instence = new Singleton();
                }
            }
        }
        return instence;
    }
}
