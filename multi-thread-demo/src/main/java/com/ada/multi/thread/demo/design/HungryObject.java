package com.ada.multi.thread.demo.design;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 饿汗式
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 16:21
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class HungryObject {
	private static HungryObject object = new HungryObject();

	public HungryObject() {
	}

	public static HungryObject getInstance() {
		return object;
	}
}
