package com.demoPack.proxy.aop.creator;

import com.demoPack.proxy.aop.interceptor.ProxyInterceptor;

import java.util.List;


/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public interface ProxyCreator {

    public <T> T createProxy(final Class<?> targetClass, final List<ProxyInterceptor> interceptors);
}
