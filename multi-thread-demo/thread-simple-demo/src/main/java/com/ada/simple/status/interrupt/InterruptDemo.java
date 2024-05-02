package com.ada.simple.status.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * <b><code></code></b>
 * <p/>
 * <p>中断demo
 * <p/>
 *
 * @Date: 2022/9/12 0:25
 * @Author xwn
 * @Version 1.0.0.1
 */
public class InterruptDemo {
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //  isInterrupted默认是true
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println(i);
            }
            System.out.println("i:" + i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);

        //作用：把isInterrupted设置成true
        thread.interrupt();

    }
}
