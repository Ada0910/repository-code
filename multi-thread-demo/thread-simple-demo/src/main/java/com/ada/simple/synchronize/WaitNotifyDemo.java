package com.ada.simple.synchronize;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/9/12 18:52
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.start();
        ThreadB threadB = new ThreadB(lock);
        threadB.start();
    }
}
