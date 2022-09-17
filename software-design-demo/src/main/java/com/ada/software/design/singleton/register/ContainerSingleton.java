package com.ada.software.design.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *单例模式之枚举式（容器缓存）
 * Spring官方就是用这种
 * <p/>
 *
 * @Date: 2022/9/17 23:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ContainerSingleton {

	private static Map<String, Object> ioc = new ConcurrentHashMap<>();

	/**
	 * 私有的构造方法
	 */
	public ContainerSingleton() {
	}

	/**
	 * 公共的获取方法
	 */
	public static Object getBean(String className) {
		//加上就可以变成线程安全
		synchronized (ioc) {
			// 如果缓存中没有对应的键值对
			if (!ioc.containsKey(className)) {
				Object obj = null;
				try {
					obj = Class.forName(className).newInstance();
					ioc.put(className, obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return obj;
			}

			return ioc.get(className);
		}
	}
}
