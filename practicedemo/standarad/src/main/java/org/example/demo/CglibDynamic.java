package org.example.demo;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cjlib动态代理，   类代理
 */
public class CglibDynamic {
    public static void main(String[] args) {
        // 创建 Enhancer 对象，用于创建代理
        Enhancer enhancer = new Enhancer();
        // 设置要代理的目标类为 MyService2
        enhancer.setSuperclass(MyService2.class);
        // 设置代理的回调对象为 MyInterceptor 的实例
        enhancer.setCallback(new MyInterceptor());
        // 创建代理对象并强制转换为 MyService2 类型
        MyService2 proxy =(MyService2) enhancer.create();
        // 调用代理对象的 doSomething 方法
        proxy.doSomething();

    }
}


class MyService2{
    public void doSomething(){
        System.out.println("doSomething");
    }
}


/**
 * cjlib 动态代理
 */
class MyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before——————————————————");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("after——————————————————");
        return o;
    }
}