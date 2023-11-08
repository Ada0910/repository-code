package com.ada.multi.thread.base.start;

/**
 * <b><code></code></b>
 * <p/>
 * JAVA实现多线程的方法一：继承Thread类,本质也是一个Runnable接口
 * <p/>
 *
 * @Date: 2022/9/8 22:40
 * @Author xwn
 * @Version 1.0.0.1
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("JAVA实现多线程的方法一：继承Thread类");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
