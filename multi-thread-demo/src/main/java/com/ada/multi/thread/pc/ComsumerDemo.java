package com.ada.multi.thread.pc;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/17 18:12
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class ComsumerDemo {
	private String lock;

	public ComsumerDemo(String lock) {
		this.lock = lock;

	}

	public void getValue() {
		try {
			synchronized (lock){
				while ("".equals(ValueObject.value)) {
					System.out.println("消费者：" + Thread.currentThread().getName() + "处于[等待]中了");
					lock.wait();
					System.out.println("消费者：" + Thread.currentThread().getName() + "处于【运行】中了");
					String value = System.currentTimeMillis() + "-" + System.nanoTime();
					ValueObject.value = value;
					lock.notify();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}