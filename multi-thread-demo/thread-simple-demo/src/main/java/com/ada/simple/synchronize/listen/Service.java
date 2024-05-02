package com.ada.simple.synchronize.listen;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 任意对象作为对象监视器
 * <p/>
 *
 * @Date: 2023/8/8 22:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Service {
	private String name;
	private String password;
	//这个成员是类的
	private String anyString = new String();

	public void setValue(String name, String password) {
		//str用这个来做同步，就可以异步执行代码块
		String str  = new String();
		synchronized (str) {
			System.out.println("线程的名字：" + Thread.currentThread().getName() + "：在" + System.currentTimeMillis() + "进入同步块");
			try {
				this.name = name;
				Thread.sleep(1000);
				this.password = password;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程的名字：" + Thread.currentThread().getName() + "：在" + System.currentTimeMillis() + "离开同步块");
		}
	}

	public static void main(String[] args) {
		Service  service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("b");
		b.start();
	}
}
