package org.example.demo;

import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock lock = new ReentrantLock();
 * // 线程1执行以下代码
 * lock.lock();
 * try {
 *     // 执行需要加锁保护的代码逻辑
 * } finally {
 *     lock.unlock();
 * }
 * 假设线程 1 获取锁之后，线程 2 也尝试获取同一把锁，线程 2 执行lock()方法时就会进入阻塞等待状态，
 * 并且如果此时线程2 被其他线程调用interrupt()方法尝试中断它，
 * 线程 2 并不会因为这个中断而停止等待获取锁的行为，它会持续阻塞，直到获取到锁或者整个程序出现其他异常情况终止运行。
 */



/**
 * 使用lockInterruptibly()方法时，线程在等待获取锁的过程中，
 * 如果被其他线程调用interrupt()方法进行了中断操作，那么该线程会立即抛出InterruptedException异常，
 * 从而可以在捕获该异常的代码块中做相应的处理，比如结束当前任务、进行资源清理等操作
 */
public class LockInterruptibly {
    public static void main(String[] args) {
        // 创建一个可重入锁对象
        ReentrantLock lock = new ReentrantLock();
        // 创建一个新的线程，使用 Lambda 表达式定义线程的执行逻辑
        Thread thread = new Thread(() -> {
            try {
                // 尝试以可中断的方式获取锁，如果在等待锁的过程中被中断，会抛出 InterruptedException
                lock.lockInterruptibly();
                try {
                    // 执行需要加锁保护的代码逻辑
                } finally {
                    // 无论如何，最终都要释放锁，防止死锁
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                // 打印信息，表示线程获取锁时被中断，并执行相应处理
                System.out.println("线程获取锁时被中断，执行相应处理");
                // 可以在这里进行如释放资源、设置线程相关状态等操作
            }
        });
        // 启动线程
        thread.start();
        // 假设在某个时刻，另一个线程执行以下操作来中断上面的 thread 线程
        thread.interrupt();
    }

}
