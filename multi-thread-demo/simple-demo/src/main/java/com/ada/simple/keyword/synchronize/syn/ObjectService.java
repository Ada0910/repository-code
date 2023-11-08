package com.ada.multi.thread.keyword.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/7 23:32
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ObjectService {

	public void serviceMethodA() {
		try {
			synchronized (this) {
				System.out.println("A线程的开始时间" + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("A线程的开始时间" + System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


	public void serviceMethodB() {
		synchronized (this) {
			System.out.println("B线程的开始时间" + System.currentTimeMillis());
			System.out.println("B线程的开始时间" + System.currentTimeMillis());
		}
	}

}
