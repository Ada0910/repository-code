package com.ada.multi.thread.communication.join;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 主线程等待子线程运行完之后再执行，可以用join
 * <p/>
 *
 * @Date: 2023/8/20 17:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MySimpleThread extends Thread{

	@Override
	public void run() {
		int s = (int) (Math.random() * 10000);
		System.out.println(s);
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MySimpleThread myThread = new MySimpleThread();
		myThread.start();
		myThread.join();
		System.out.println("我想当myThread对象的主线程执行完毕之后我再执行，我do it");
	}
}
