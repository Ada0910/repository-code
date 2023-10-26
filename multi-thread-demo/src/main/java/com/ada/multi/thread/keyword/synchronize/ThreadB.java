package com.ada.multi.thread.keyword.synchronize;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * notify机制
 * <p/>
 *
 * @Date: 2022/9/12 18:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB extends Thread {
    private Object lock = new Object();


    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start ThreadB");
            lock.notify(); //唤醒被阻塞的线程
            System.out.println("end ThreadB");
        }
    }
}
