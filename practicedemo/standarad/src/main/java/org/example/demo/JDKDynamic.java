package org.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//jdk动态代理
public class JDKDynamic {



    public static void main(String[] args) {
        MyServiceImpl target = new MyServiceImpl();
        /*
        * jdk代理 参数
        * 实现类的类加载启
        * 实现类数显的接口
        * 动态代理处理器（加强后的功能）
        * 返回接口对象
        *
        * */
        MyService m = (MyService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyInvocationHandler(target)
        );

        //接口对象调用方法
        m.doSomething();

    }



}


//必须有个接口
interface MyService{
    void doSomething();
}

//有个接口的实现类
class MyServiceImpl implements MyService{
    @Override
    public void doSomething(){
        System.out.println("doSomething");
    }
}



//动态代理处理器(功能增强)
class MyInvocationHandler   implements InvocationHandler{

    private  final Object target;

    public MyInvocationHandler(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before——————————————————");
        Object invoke = method.invoke(target, args);
        System.out.println("after——————————————————");
        return invoke;
    }
}