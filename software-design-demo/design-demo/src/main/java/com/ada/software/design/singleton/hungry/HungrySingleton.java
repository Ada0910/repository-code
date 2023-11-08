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
 * @Date: 2022/9/15 19:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class HungrySingleton {

    //用静态变量在类加载的时候就会先初始化
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    /**
     * 构造方法私有
     */
    private HungrySingleton() {
    }

    /**
     * 提供一个全局的访问点
     */
    public static HungrySingleton getInstance() {
        return  hungrySingleton;
    }
}
