package com.ada.simple.demo.fork;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 
 *  Java工程在主线程执行期间开一个子线程执行任务，主线程等待子线程成功后继续执行
 *  使用ExecutorService创建线程池（数量为1）的方式
 *  
 * <p/>
 * <b>Creation Time:</b> 2023/12/7 14:11
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ForkSubThread3 {
	public static void main(String[] args) throws Exception {
		//创建一个容量为1的线程池
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		String s = "main";
		CustomCallable cRunnacle = new CustomCallable(s);
		Future<String> submit = executorService.submit(cRunnacle);
		System.out.println("主线程做自己的事情--start");
		System.out.println("获取子线程返回结果："+submit.get());//获取返回结果时会阻塞
		System.out.println("主线程做自己的事情--end");
		//关闭线程池
		executorService.shutdown();

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
