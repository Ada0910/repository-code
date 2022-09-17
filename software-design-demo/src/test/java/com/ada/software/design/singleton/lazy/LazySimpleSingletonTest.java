package com.ada.software.design.singleton.lazy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/9/17 18:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazySimpleSingletonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new LazySimpleSingletonThread());
        Thread t2 = new Thread(new LazySimpleSingletonThread());
        t1.start();
        t2.start();


    }
}
