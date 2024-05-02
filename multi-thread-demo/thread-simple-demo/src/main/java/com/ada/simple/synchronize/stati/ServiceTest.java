package com.ada.simple.synchronize.stati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/13 15:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ServiceTest {
	public static void main(String[] args) {
		ThreadA a = new ThreadA();
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB();
		b.setName("B");
		b.start();
	}
}
