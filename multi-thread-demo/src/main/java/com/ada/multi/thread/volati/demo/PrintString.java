package com.ada.multi.thread.volati.demo;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 单线程
 * <p/>
 *
 * @Date: 2023/8/13 18:04
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PrintString {
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
	}

	public static void main(String[] args) {
		PrintString printString = new PrintString();
		printString.printStringMethod();
		System.out.println("开始设置线程的打印值，尝试停止线程的打印");
		printString.setPrint(false);
	}
}
