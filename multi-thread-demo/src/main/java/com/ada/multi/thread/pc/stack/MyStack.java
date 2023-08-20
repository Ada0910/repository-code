package com.ada.multi.thread.pc.stack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 12:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyStack {
	private List list = new ArrayList();

	synchronized public void push() {
		try {
			if (list.size() == 1) {
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		list.add("列表的值：" + Math.random());
		this.notify();

		System.out.println("经过方法push之后的数量是：" + list.size() +"个");
	}


	public synchronized String pop() {
		String returnValue = "";

		try {
			if (list.size() == 0) {
				System.out.println("方法pop操作中的 ：" + Thread.currentThread().getName() + "线程是 WAIT状态！");
				this.wait();
			}

			returnValue = "" + list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop = " + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}
