package com.ada.simple.status;

import java.util.concurrent.TimeUnit;

/**
 * <b><code></code></b>
 * <p/>
 *  演示线程的状态转化
 *
 * 线程的六种状态
 * （1）NEW
 * （2）WAITING
 * （3）RUNNABLE
 * （4）BLOCKED
 * （5）TIME_WAITING
 * （6）TERMINATED
 * <p>
 * <p/>
 *
 * @Date: 2022/9/12 0:04
 * @Author xwn
 * @Version 1.0.0.1
 */
public class ThreadStatus {

    /**
     * 如何通过命令行查看状态
     *  cd target目录下 -> jps   (4716 ThreadStatus)
     *
     * 然后再-> jstack 4716，就可以看到打印的消息
     */
    public static void main(String[] args) {

        //构建一个Time_Waiting_Thread的线程
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Time_Waiting_Thread").start();


        //  Wating_Thread线程
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "").start();

        //构建一个BLOCKED的类
        new Thread(new BlockedDemo(), "Blocke01_Thread").start();
        new Thread(new BlockedDemo(), "Blocke02_Thread").start();


    }


    // 模拟三：BLOCKED
    static class BlockedDemo extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (BlockedDemo.class) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


