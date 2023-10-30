package com.ada.multi.thread.demo.design;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 饿汉式，就是掉get方法的时候，调用
 * <p/>
 * <b>Creation Time:</b> 2023/8/29 16:26
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class LazyObject {
	private static LazyObject object ;

	public LazyObject() {
	}
	public static LazyObject getInstance() {
		if(object ==null){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (LazyObject.class) {
				//if (object == null) {
				//	object = new LazyObject();
				//}
				object = new LazyObject();
			}
		}
		return object;
	}
}
