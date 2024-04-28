package com.ada.simple.demo.fork;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * Java工程在主线程执行期间开一个子线程执行任务，主线程等待子线程成功后继续执行
 * FutureTask+Callable
 * 
 * <p/>
 * <b>Creation Time:</b> 2023/12/7 14:06
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ForkSubThread2 {
	public static void main(String[] args) throws Exception {
		String s = "main";
		CustomCallable cRunnacle = new CustomCallable(s);
		FutureTask<String> futureTask = new FutureTask<String>(cRunnacle);
		Thread thread = new Thread(futureTask,"子线程");
		thread.start(); //子线程执行

		System.out.println("主线程做自己的事情--start");
		System.out.println("获取子线程返回结果："+futureTask.get());//获取返回结果时会阻塞
		System.out.println("主线程做自己的事情--end");

	}

	static final class CustomCallable implements Callable<String> {
		String s;
		public CustomCallable(String s) {
			this.s = s;
		}
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName() + ":执行 start");
			Thread.sleep(2000); //子线程停留2秒
			System.out.println(Thread.currentThread().getName() + ":执行 end");
			return s+":sub";
		}
	}
}