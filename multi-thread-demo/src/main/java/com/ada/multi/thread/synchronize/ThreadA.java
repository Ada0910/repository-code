package com.ada.multi.thread.synchronize;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * wait机制
 * <p/>
 *
 * @Date: 2022/9/12 18:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start ThreadA");
            try {
                lock.wait(); //实现线程的阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ThreadA");
        }
    }
}
