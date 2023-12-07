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
public class LazyDoubleCheckSingletonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new LazyDoubleCheckSingletonThread());
        Thread t2 = new Thread(new LazyDoubleCheckSingletonThread());
        t1.start();
        t2.start();


    }
}
