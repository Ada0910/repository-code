package com.ada.software.design.singleton.lazy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/9/17 18:13
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazyDoubleCheckSingletonThread implements  Runnable{
    @Override
    public void run() {
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+":"+lazyDoubleCheckSingleton);
    }
}
