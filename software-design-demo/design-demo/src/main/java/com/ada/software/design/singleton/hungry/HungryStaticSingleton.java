package com.ada.software.design.singleton.hungry;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单例模式之饿汉式
 * 在类创建的时候就立即初始化，并创建单例对象
 *
 * 缺点：浪费内存，因为不管有没有用，都是会实例化对象
 * <p/>
 *
 * @Date: 2022/9/17 17:20
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class HungryStaticSingleton {

    private  static  final  HungryStaticSingleton hungryStaticSingleton ;

    //静态块的加载是比较先的
    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    /**
     * 私有的构造方法
     */
    private HungryStaticSingleton() {
    }


    /**
     * 全局的访问点
     */
    public static  HungryStaticSingleton getInstance(){
        return hungryStaticSingleton;
    }
}
