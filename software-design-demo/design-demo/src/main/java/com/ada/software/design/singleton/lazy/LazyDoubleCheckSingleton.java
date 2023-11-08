package com.ada.software.design.singleton.lazy;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *单例模式之懒汉式（双重校验）
 *只有在外部调用的时候，才会初始化对象
 *
 * <p/>
 *
 * @Date: 2022/9/17 18:30
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LazyDoubleCheckSingleton {
    // 静态的变量
    private  static   LazyDoubleCheckSingleton lazyDoubleCheckSingleton;

    /**
     * 构造方法私有化
     */
    private LazyDoubleCheckSingleton() {
    }

    /**
     * 全局调用方法
     */
    public static  LazyDoubleCheckSingleton getInstance(){

        /*
         * 判断LazyDoubleCheckSingleton是否为空，不然每次都要创建
         * 从安全的或者单例的角度来说，第一层判断是可以去掉的，但是这样会引发一个问题，就是一调用这个方法，就会直接进入等待
         * 严重的降低效率（即线程安全，但是效率低下）
         *
         */
        if (null == lazyDoubleCheckSingleton) {
            //因为在多线程下，这里可能会存在线程安全的问题，所以加上类锁
            synchronized (LazyDoubleCheckSingleton.class) {
                //第二个校验如果去掉，会存在实例化两次，那就没有意思了
                if (null == lazyDoubleCheckSingleton) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }

}
