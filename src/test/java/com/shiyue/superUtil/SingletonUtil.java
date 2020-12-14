package com.shiyue.superUtil;

public class SingletonUtil {

    private SingletonUtil(){


    }

    private static SingletonUtil instance = null;

    private static SingletonUtil getInstance(){
        if (instance!=null) {
            synchronized (SingletonUtil.class) {
                if (instance != null) {
                    instance = new SingletonUtil();
                }
            }
        }
        return instance;
    }
}
