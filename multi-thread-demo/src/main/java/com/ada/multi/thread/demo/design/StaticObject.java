package com.ada.multi.thread.demo.design;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 静态单例类
 * <p/>
 *
 * @Date: 2023/8/29 23:30
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class StaticObject {
	private static class ObjectHandler {
		private static StaticObject staticObject = new StaticObject();
	}

	public StaticObject() {
	}

	public static StaticObject getInstance() {
		return ObjectHandler.staticObject;
	}
}
