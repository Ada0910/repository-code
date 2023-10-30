package com.ada.multi.thread.base.atom;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/10/27 15:08
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class User {
	private String name;
	//必须是 public volatile
	//否则会报java.lang.ExceptionInInitializerError
	//Caused by: java.lang.IllegalArgumentException: Must be volatile type
	//at java.util.concurrent.atomic.AtomicIntegerFieldUpdater$AtomicIntegerFieldUpdaterImpl.<init>(AtomicIntegerFieldUpdater.java:407)
	//at java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdater.java:87)
	public volatile int old;
	public User(String name, int old) {
		this.name = name;
		this.old = old;
	}
	public String getName() {
		return name;
	}
	public int getOld() {
		return old;
	}
}
