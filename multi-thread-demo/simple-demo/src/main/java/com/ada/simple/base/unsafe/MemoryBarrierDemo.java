package com.ada.multi.thread.base.unsafe;

import sun.misc.Unsafe;


/**
 *
 * <b><code></code></b>
 * <p/>  
 * 内存屏障Demo
 *
 * 内存屏障，禁止load操作重排序。屏障前的load操作不能被重排序到屏障后，屏障后的load操作不能被重排序到屏障前
 * public native void loadFence();
 *
 * 内存屏障，禁止store操作重排序。屏障前的store操作不能被重排序到屏障后，屏障后的store操作不能被重排序到屏障前
 * public native void storeFence();
 *
 * 内存屏障，禁止load、store操作重排序
 * public native void fullFence();
 * <p/>
 *
 * @Date: 2023/6/23 23:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MemoryBarrierDemo implements Runnable {
	boolean flag = false;

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ChangeThread  线程的thread值：" + flag);
		flag = true;
	}

	public static void main(String[] args) {
		Unsafe unsafe = UnsafeInstance.getUnsafeInstance();
		MemoryBarrierDemo changeThread = new MemoryBarrierDemo();
		new Thread(changeThread).start();
		while (true) {
			boolean flag = changeThread.isFlag();
			//unsafe.loadFence(); //加入读内存屏障
			if (flag) {
				System.out.println("检测到Flag的变化！");
				break;
			}
		}
		System.out.println("主线程执行完毕");
	}

	private boolean isFlag() {
		return flag;
	}
}
