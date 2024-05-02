package com.ada.simple.synchronize;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Synchronized的作用范围
 *
 * 1.- 修饰实例方法
 * 2.- 修改静态方法
 * 3.- 修饰代码块
 *
 * <p/>
 *
 * @Date: 2022/9/12 17:03
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SynchronizedDemo {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        /*
         * 循环1000次，但是结果却没有出现如期的1000,
         *
         * 方法一：在inc方法中增加synchronized即可实现同步（类锁）
         * 方法二：同步代码块（类锁）
         *
         * */
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                SynchronizedDemo.inc();
            }).start();
        }

        Thread.sleep(3000);
        System.out.println(" 运行结果" + count);
    }

    //增加count的方法
    public static /* synchronized*/ void inc() {
        // synchronized (SynchronizedDemo.class) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    // }

}
