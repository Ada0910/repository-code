package com.ada.simple.status.start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <b><code></code></b>
 * <p/>
 * JAVA实现多线程的方法s三：实现Callable（有返回值）
 * <p/>
 *
 * @Date: 2022/9/8 23:00
 * @Author xwn
 * @Version 1.0.0.1
 */
public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return "JAVA实现多线程的方法s三：实现Callable（有返回值）";
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future future = threadPool.submit(myCallable);
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
