package com.ada.multi.thread.fundamental;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/21 16:54
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class DataImportThread extends Thread {

	private CyclicBarrier cyclicBarrier;

	private String path;

	public DataImportThread(CyclicBarrier cyclicBarrier, String path) {
		this.cyclicBarrier = cyclicBarrier;
		this.path = path;
	}

	@Override
	public void run() {
		System.out.println("开始导入：" + path + " 数据");
		//TODO
		try {
			//阻塞 condition.await()
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
