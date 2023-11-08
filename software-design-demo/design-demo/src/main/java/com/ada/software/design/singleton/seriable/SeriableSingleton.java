package com.ada.software.design.singleton.seriable;

import java.io.Serializable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 懒汉式+加上了序列化
 *
 * <p/>
 *
 * @Date: 2022/9/17 19:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SeriableSingleton implements Serializable {


	/*
	 *序列化就是说把内存中的状态通过转换成字节码的形式
	 *从而转换一个IO流，写入到其他地方(可以是磁盘、网络IO)
	 *内存中状态给永久保存下来了

	 *反序列化
	 *讲已经持久化的字节码内容，转换为IO流
	 *通过IO流的读取，进而将读取的内容转换为Java对象
	 *在转换过程中会重新创建对象new
	 */

	public final static SeriableSingleton INSTANCE = new SeriableSingleton();


	/**
	 * 构造方法私有
	 */
	private SeriableSingleton() {
	}

	/**
	 * 提供一个全局的访问点
	 */
	public static SeriableSingleton getInstance() {
		return INSTANCE;
	}


	/**
	 * 重写一个readResolve，在序列化的时候就能够保证序列化和反序列化
	 */
	private Object readResolve() {
		return INSTANCE;
	}

}
