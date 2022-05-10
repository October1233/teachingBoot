package com.shiyue.proxy.service.impl;

import com.shiyue.proxy.service.Subject;

public class RealSubject implements Subject
{

    /**
     * 你好
     *
     * @param name
     * @return
     */
    public String SayHello(String name)
    {
        return "hello " + name;
    }

    /**
     * 再见
     *
     * @return
     */
    public String SayGoodBye()
    {
        return " good bye ";
    }
}

