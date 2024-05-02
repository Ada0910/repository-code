package com.ada.simple.status.suspend;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/5 23:46
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SuspendPrintDemo extends Thread {

	private long i = 0;

	@Override
	public void run() {
		while (true) {
			i++;
		//	如果把下面的注释放掉，则不会打印出主线程结束，因为当程序运行到print（）方法时，同步锁未释放
		//	System.out.println(i);
		}
	}

	public static void main(String[] args) {
		try {
			SuspendPrintDemo demo = new SuspendPrintDemo();
			demo.start();
			demo.sleep(100);
			demo.suspend();
			System.out.println("主线程结束！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
