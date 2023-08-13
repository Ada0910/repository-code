package com.ada.multi.thread.synchronize.stati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/13 15:37
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Service {

	 synchronized public static void printA() {
		 System.out.println("线程的名称为：" + Thread.currentThread().getName() +"在："+ System.currentTimeMillis() +"进入了方法A" );
		 try {
			 Thread.sleep(3000);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 System.out.println("线程的名称为：" + Thread.currentThread().getName() +"在："+ System.currentTimeMillis() +"离开了方法A" );
	}

	synchronized public static void printB() {
		System.out.println("线程的名称为：" + Thread.currentThread().getName() +"在："+ System.currentTimeMillis() +"进入了方法B" );
		System.out.println("线程的名称为：" + Thread.currentThread().getName() +"在："+ System.currentTimeMillis() +"离开了方法B" );
	}
}
