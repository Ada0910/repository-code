package com.ada.multi.thread.demo.pc.stack;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 13:00
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyStackTest {
	public static void main(String[] args) {
		MyStack myStack = new MyStack();

		P p = new P(myStack);
		ThreadP threadP = new ThreadP(p);
		threadP.start();


		//	一个生产者，多个消费者
		C c = new C(myStack);
		ThreadC threadC = new ThreadC(c);
		threadC.start();

	}
}
