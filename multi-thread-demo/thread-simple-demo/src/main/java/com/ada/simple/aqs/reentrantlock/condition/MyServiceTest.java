package com.ada.simple.aqs.reentrantlock.condition;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/27 12:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyServiceTest {
	public static void main(String[] args) throws InterruptedException {

		/**
		 * 会报下面的错误
		 * Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
		 * 	at java.lang.Object.wait(Native Method)
		 * 	at java.lang.Object.wait(Object.java:502)
		 * 	at com.ada.multi.thread.lock.condition.MyService.await(MyService.java:25)
		 * 	at com.ada.multi.thread.lock.condition.ThreadA.run(ThreadA.java:24)
		 *
		 * 	解决的办法：
		 * 	必须在condition.await()方法调用之前调用lock.lock()
		 * 	类MyService用方法waitMethod
		 */
		//MyService service = new MyService1();
		//ThreadA a = new ThreadA(service);
		//a.start();
		//
		//// 这个sleep很关键，由于线程是随机运行的，有时候唤醒进程可能会快一点
		//Thread.sleep(3300);
		////	 唤醒进程
		//service.signal();


	}
}
