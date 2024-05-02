package com.ada.simple.base.inner;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 对class2加上锁之后，其他线程之能以同步的方式调用class2中的静态同步方法
 * <p/>
 *
 * @Date: 2023/8/13 17:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class OutClass2 {
	static class InnerClass {
		public void method1(InnerClass2 class2) {
			String name = Thread.currentThread().getName();
			synchronized (class2) {
				System.out.println(name + "进入到InnerClass1类中的method1方法");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name + "离开InnerClass1类中的method1方法");
			}
		}


		public synchronized void method2() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "进入到InnerClass1类中的method2方法");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "离开InnerClass1类中的method2方法");
		}
	}

	static class InnerClass2 {
		public synchronized void method1() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "进入到InnerClass2类中的method1方法");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "离开InnerClass2中的method1方法");
		}
	}

	public static void main(String[] args) {
		InnerClass innerClass = new InnerClass();
		InnerClass2 innerClass2 = new InnerClass2();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				innerClass.method1(innerClass2);
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				innerClass.method2();
			}
		});


		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				innerClass2.method1();
			}
		});

		t1.start();
		t2.start();
		t3.start();
	}
}
