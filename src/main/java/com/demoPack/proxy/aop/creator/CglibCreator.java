package com.demoPack.proxy.aop.creator;

import com.demoPack.proxy.aop.chain.CglibProxyChain;
import com.demoPack.proxy.aop.interceptor.ProxyInterceptor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 王萍 on 2017/11/12 0012.
 */
public class CglibCreator implements ProxyCreator {
    public <T> T createProxy(final Class<?> targetClass,final List<ProxyInterceptor> interceptors) {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
                return new CglibProxyChain(targetClass, targetObject, method, methodProxy, params, interceptors).doChain();
            }
        });
    }
}
