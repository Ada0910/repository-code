package com.ada.simple.base.volati.demo;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 多线程
 * <p/>
 *
 * @Date: 2023/8/13 18:04
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PrintString2 implements Runnable {
	private boolean isPrint = true;

	public boolean isPrint() {
		return isPrint;
	}

	public void setPrint(boolean print) {
		isPrint = print;
	}

	public void printStringMethod() {
		while (isPrint) {
			System.out.println("当前的线程名是：" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("当前的线程被停止了！");
	}

	public static void main(String[] args) {
		PrintString2 printString = new PrintString2();
		new Thread(printString).start();
		printString.printStringMethod();
		System.out.println("开始设置线程的打印值，尝试停止线程的打印");
		printString.setPrint(false);
	}

	@Override
	public void run() {
		printStringMethod();
	}
}
