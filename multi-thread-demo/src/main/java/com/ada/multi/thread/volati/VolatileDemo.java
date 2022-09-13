package com.ada.multi.thread.volati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Volatile测试Demo
 * <p/>
 *
 * @Date: 2022/9/13 21:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class VolatileDemo {
    public static /*volatile*/ Boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            int i=0;
            while(!stop){ //condition 不满足
                i++;
                System.out.println("i:"+i);
            }
        });
        t1.start();
        Thread.sleep(1000);
        stop=true; //true
    }
}
