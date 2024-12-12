package org.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InheritableThreadLocalExample {

    // 创建InheritableThreadLocal对象，用于存储共享数据
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    static ThreadLocal<String> objectThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程中设置数据
        inheritableThreadLocal.set("这是父线程的数据");
        objectThreadLocal.set("这是父线程的数据ThreadLocal");

        // 创建线程池，这里简单使用一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 提交任务给线程池，由子线程执行任务
        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                // 子线程中获取并打印从父线程继承的数据
                String data = inheritableThreadLocal.get();
                String s = objectThreadLocal.get();
                System.out.println(Thread.currentThread().getName() + " 获取到的数据: " + data);
                System.out.println(Thread.currentThread().getName() + " 获取到的数据ThreadLocal: " + s);
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }
}