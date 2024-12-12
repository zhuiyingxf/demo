package org.example.javaspi;

import java.util.ServiceLoader;


/**
 * JAVAspi  案例
 */
public class JavaSpiTest {
    public static void main(String[] args) {

        //加载接口，获取   META-INF/services/com.example.javaspi.MyServiceSpi     文件里面的实现类
        ServiceLoader<MyServiceSpi> serviceLoader = ServiceLoader.load(MyServiceSpi.class);
        //可配置多个
        for (MyServiceSpi service : serviceLoader) {
            service.execute();
        }

    }

}


