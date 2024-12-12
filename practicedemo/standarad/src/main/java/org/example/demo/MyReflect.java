package org.example.demo;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflect {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //  1  获取class对象
        Class<?> clazz = Class.forName("com.example.demo.DemoReflect");
                 /*  // 或者
                Class<?> clazz = MyClass.class;
                // 或者
                Class<?> clazz = obj.getClass();*/


        // 2.  创建对象
               // Object o = clazz.newInstance();    //已过时

                 Constructor<?> constructor = clazz.getConstructor();
                Object obj = constructor.newInstance();

        //3.  访问字段
                Field name = clazz.getDeclaredField("name");
                name.setAccessible(true);  //允许访问
                Object o = name.get(obj);
                name.set(obj,"newaaa");


         //4.调用方法
                Method method = clazz.getMethod("doSomething");//可以增加可变方法参数列表类型
                Object result = method.invoke(obj);  //可以传入对应参数

                Method method2 = clazz.getMethod("doSomething2", String.class);//可以增加可变方法参数列表类型
                Object result2 = method2.invoke(obj,"newaaa");  //可以传入对应参数



    }
}

@Data
class DemoReflect{

    private String name;
    public int id;

    public void doSomething(){
        System.out.println("I am do something  DemoReflect");
    }

    public void doSomething2( String name){
        System.out.println("I am do something  DemoReflect " + name);
    }

}