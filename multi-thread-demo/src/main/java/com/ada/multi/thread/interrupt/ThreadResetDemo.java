package com.ada.multi.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 线程复位
 * 这里的复位指的是：线程的状态复位到原先，比如isInterrupted复位到默认的false
 *
 * 线程复位的方法：
 * （1）Thread.interrupted();
 * （2）InterruptedException
 * <p/>
 *
 * @Date: 2022/9/12 0:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadResetDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        //testInterrupted();
        testInterruptedException();


    }


    /**
     * 测试线程复位方法：Thread.interrupted();
     */
    private static void testInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    //复位，回到原始状态
                    Thread.interrupted();
                    System.out.println("before:" + Thread.currentThread().isInterrupted());

                }

            }
        });
        //启动线程
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        //把isInterrupted设置成true
        thread.interrupt();

    }


    /**
     * 线程复位方法二：InterruptedException
     */
    private static void testInterruptedException() throws InterruptedException {

        Thread thread = new Thread(() -> {
            //isInterrupted方法默认是false
            while (!Thread.currentThread().isInterrupted()) {
                //  断一个处于阻塞状态的线程。join/wait/queue.take..
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程表示已经得到中断信号，并且已经抛出和复位，你可以进行捕获！但是处不处理完全是由你来的");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //中断之后可以处理,也可以选择不处理
                    // 处理
                    //  break;
                    System.out.println("break");
                }

            }
            System.out.println("i:" + i);

        });
        thread.start();
        System.out.println(Thread.currentThread().isInterrupted());
        //Object.wait、Thread.sleep 等被阻塞的线程被唤醒以后会
        //通过 is_interrupted 方法判断中断标识的状态变化，如果发
        //现中断标识为 true，则先清除中断标识，然后抛出InterruptedException
        TimeUnit.SECONDS.sleep(1);

        //把isInterrupted设置成true
        thread.interrupt();

        System.out.println(thread.isInterrupted()); //true
        System.out.println(Thread.currentThread().isInterrupted());

    }


}
