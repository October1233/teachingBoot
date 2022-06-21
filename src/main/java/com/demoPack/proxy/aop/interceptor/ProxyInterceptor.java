package com.demoPack.proxy.aop.interceptor;


import com.demoPack.proxy.aop.chain.DefaultProxyChain;

/**
 * Created by 王萍 on 2017/11/11 0011.
 */
public interface ProxyInterceptor {

    Object intercept(DefaultProxyChain chain) throws Throwable;

    boolean support();
}
