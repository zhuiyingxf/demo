package org.example.demo;

public class DemoThreadLocal {
    private static ThreadLocal<String> threadLocalUserName = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            threadLocalUserName.set("Alice");
            System.out.println("Thread 1 - User Name: " + threadLocalUserName.get());
        });

        Thread thread2 = new Thread(() -> {
            threadLocalUserName.set("Bob");
            System.out.println("Thread 2 - User Name: " + threadLocalUserName.get());
        });

        thread1.start();
        thread2.start();
    }
}
