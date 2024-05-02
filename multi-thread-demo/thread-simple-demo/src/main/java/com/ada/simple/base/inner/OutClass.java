package com.ada.simple.base.inner;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 内置类使用的是不同的锁
 * <p/>
 *
 * @Date: 2023/8/13 17:10
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class OutClass {
	//内部类
	static class Inner {
		public void method1() {
			synchronized ("其他的锁") {
				for (int i = 0; i < 10; i++) {
					System.out.println("当前执行的线程名：" + Thread.currentThread().getName() + ",i = " + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}


		public synchronized void method2() {
			for (int i = 0; i < 20; i++) {
				System.out.println("当前执行的线程名：" + Thread.currentThread().getName() + ",i = " + i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		Inner inner = new Inner();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				inner.method1();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				inner.method2();
			}
		});

		t1.start();
		t2.start();
	}
}
