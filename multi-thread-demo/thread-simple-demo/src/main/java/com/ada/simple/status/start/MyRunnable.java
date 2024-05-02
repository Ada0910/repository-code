package com.ada.simple.status.start;

/**
 * <b><code></code></b>
 * <p/>
 * JAVA实现多线程的方法二：实现Runnable接口
 * <p/>
 *
 * @Date: 2022/9/8 22:46
 * @Author xwn
 * @Version 1.0.0.1
 */
public class MyRunnable  implements Runnable{
    @Override
    public void run() {
        System.out.println("JAVA实现多线程的方法二：实现Runnable接口");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        Thread thread2 = new Thread(myRunnable);
        thread2.start();
    }
}
