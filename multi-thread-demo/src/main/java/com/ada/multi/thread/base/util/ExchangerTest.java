package com.ada.multi.thread.base.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/10/30 11:32
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since 1.0.0.1
 */
public class ExchangerTest {
	public static final Exchanger<String> ex = new Exchanger<>();
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				String A = "银行流水A";
				try {
					ex.exchange(A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				String B = "银行流水B";
				try {
					String A = ex.exchange("B");
					System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是："
							+ A + "，B录入是：" + B);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		threadPool.shutdown();
	}
}
