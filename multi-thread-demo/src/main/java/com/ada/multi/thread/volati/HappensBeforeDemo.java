package com.ada.multi.thread.volati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * HappensBefore Demo
 *
 * 哪些需要happens-before规则
 * （1）程序的顺序规则
 * （2）volatile规则
 *  (3)传递性规则
 * （4）start原则
 *  (5)join原则
 *  (6)synchronized监视器规则
 *
 * <p/>
 *
 * @Date: 2022/9/13 23:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class HappensBeforeDemo {
    int a=0;
    volatile  boolean flag=false;

    /*
     * (1)程序的顺序规则
     */
    public void writer(){ //线程A
        a=1;             //1
        flag=true;       //2
        //1 happens-before 2
    }

/*
 *（2）volatile规则
 * 2 happens-before 3
 *
 * (3)传递性规则
 * 1 happens-before 2  ,2 happens-before 3 ,3 happens-before 4
 *
 */
    public void reader(){
        //3 happens-before 4（ 3对于4是可见）
        if(flag){  //3
            int x=a; //4
        }
    }


    static int x=0;

    public static void main(String[] args) throws InterruptedException {

        /*
         * (4)start原则
         * 主线程的值对于t1l来说，是可见的
         */
        Thread t1=new Thread(()->{
            //use x=10
        });

        x=10;
        t1.start();

        /*
         * (5)join原则
         * 主线程的值对于t1l来说，是可见的
         */
         Thread t2=new Thread(()->{
            x=100;
        });
        t2.start();
        t2.join();
        System.out.println(x);

        Thread t4=new Thread(()->{
            System.out.println("t");
            //执行的结果对于主线程可见
        });
        Thread t5=new Thread(()->{
            System.out.println("t5");
        });
        Thread t6=new Thread(()->{
            System.out.println("t6");
        });
        t4.start();
        t4.join(); //阻塞主线程 wait/notify
        //等到阻塞释放
        //获取到t1线程的执行结果.
        t5.start();
        t5.join(); // 建立一个happens-bebefore规则

        t6.start();

    }

    /*
     *(6)synchronized监视器规则
     * 锁的释放对于其他是可见的
     */
    public void demo() {
        synchronized (this) {//ThreadA / ThreadB
        }
    }
}
