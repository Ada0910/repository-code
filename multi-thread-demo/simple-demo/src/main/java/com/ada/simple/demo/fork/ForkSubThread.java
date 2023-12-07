package com.ada.simple.demo.fork;

/**
 *
 * <b><code></code></b>
 * <p/>
 * Java工程在主线程执行期间开一个子线程执行任务，主线程等待子线程成功后继续执行
 * <p/>
 * <b>Creation Time:</b> 2023/12/7 14:01
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ForkSubThread {
	public static void main(String[] args) throws Exception {
		String s = "main";
		CustomRunnable cRunnacle = new CustomRunnable(s);
		Thread thread = new Thread(cRunnacle,"子线程");
		thread.start(); //子线程执行
		System.out.println("主线程做自己的事情");
		thread.join(); //等待子线程执行完毕，这里会阻塞
		System.out.println("获取子线程返回结果："+cRunnacle.getData());
	}

	static final class CustomRunnable implements Runnable {
		private String s;
		public CustomRunnable(String s) {
			this.s = s;
		}
		private String a = "";
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + ":执行 start");
				Thread.sleep(2000); //子线程停留2秒
				System.out.println(Thread.currentThread().getName() + ":执行 end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			a = s + ":sub";
		}
		private String getData() {
			return a;
		}
	}
}
