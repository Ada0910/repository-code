package com.ada.multi.thread.base.priority;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/6 13:01
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyThread2  extends  Thread{

	@Override
	public void run() {
		System.out.println(currentThread().getName() + "：【MyThrea2】的优先级是；" + this.getPriority());
	}
}
