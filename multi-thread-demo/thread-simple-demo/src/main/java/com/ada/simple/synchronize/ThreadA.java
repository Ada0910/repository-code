package com.ada.simple.synchronize;

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
                /**
                 * 实现线程的阻塞
                 * 这句话就是把当前的线程移到等待队列中，等待有对应的notify或者notifyAll来唤醒（从等待队列中把该线程移到同步队列中，可以重新竞争锁）
                 * 同时会释放锁，不然其他线程永远是获取不到锁的（lock这个锁）
                 */
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ThreadA");
        }
    }
}
