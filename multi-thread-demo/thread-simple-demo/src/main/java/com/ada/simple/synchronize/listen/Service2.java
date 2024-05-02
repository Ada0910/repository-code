package com.ada.simple.synchronize.listen;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/8 23:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Service2 {

	private String anyString = new String();

	public void a() {
		synchronized (anyString) {
			System.out.println("方法A开始了");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("方法A执行完毕");
		}
	}

	 public synchronized void b() {
		System.out.println("方法B开始执行了！");
		System.out.println("方法B执行完毕了！");
	}

	public static void main(String[] args) {
		Service2  service = new Service2();
		MyThreadA a = new MyThreadA(service);
		a.setName("A");
		a.start();
		MyThreadB b = new MyThreadB(service);
		b.setName("b");
		b.start();
	}
}
