package com.ada.simple.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/7 23:48
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class TaskTest {
	public static void main(String[] args) {
		Task task = new Task();
		MyThreadA a = new MyThreadA(task);
		a.start();
		MyThreadB  b = new MyThreadB(task);
		b.start();
	}
}
