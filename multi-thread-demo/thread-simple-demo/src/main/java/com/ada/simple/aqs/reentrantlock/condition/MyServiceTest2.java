package com.ada.simple.aqs.reentrantlock.condition;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 16:38
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyServiceTest2 {
	public static void main(String[] args) throws InterruptedException {
		MyService1 myService1 = new MyService1();
		ThreadA a = new ThreadA(myService1);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(myService1);
		b.setName("B");
		b.start();

		Thread.sleep(2000);
		//myService1.signalA();
		myService1.signalB();
	}
}
