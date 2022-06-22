package com.karister;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author karister
 * @create 2021-09-07 16:28
 */


public class ThreadPool {
    public static void main(String[] args) {
        /**
         * 创建这些线程池的方法都被封装到Executors这个类中
         * FixedThreadPool：线程数固定的线程池；
         * CachedThreadPool：线程数根据任务动态调整的线程池；
         * SingleThreadExecutor：仅单线程执行的线程池
         */
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("" + i);
            threadPool.submit(task);
        }
        System.out.println(threadPool.isShutdown());
        /**
         * 线程池在程序结束的时候要关闭。
         * shutdown()它会等待正在执行的任务先完成，然后再关闭
         * shutdownNow()会立刻停止正在执行的任务
         * awaitTermination()则会等待指定的时间让线程池关闭
         */
        threadPool.shutdown();
        System.out.println(threadPool.isShutdown());
    }
}

class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("end task " + name);
    }
}
