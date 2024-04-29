package com.ada.simple.blockqueue.user;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 业务场景：
 * 就是用户注册的时候，在注册成功以后发放积分
 *
 * 改造后的代码（用阻塞队列）
 *
 * 常见的阻塞队列：
 *
 * ArrayBlockingQueue: 有界阻塞
 * LinkedBlockingQueue: 有界阻塞
 *
 * PriorityBlockingQueue：支持优先级排序无界阻塞队列
 * DelayQueue：优先级队列实现的无界阻塞队列
 * <p/>
 *
 * @Date: 2022/10/4 12:02
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class UserServiceBlockQueue {

	// 线程池
	private final ExecutorService single = Executors.newSingleThreadExecutor();

	private volatile boolean isRunning = true;
	//阻塞队列
	ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

	/*
	 * 构造代码块（执行的顺序： 静态>构造代码块>构造函数）
	 *
	 */ {
		init();
	}

	// 初始化
	private void init() {
		single.execute(() -> {
			while (isRunning) {
				try {
					// 阻塞的方式获取队列中的数据
					User user = (User) arrayBlockingQueue.take();
					sendPoints(user);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 注册新用户
	private void register() {
		User user = new User();
		user.setName("Mic");
		addUser(user);
		// 添加到异步队列
		arrayBlockingQueue.add(user);

	}


	// 添加用户
	private void addUser(User user) {
		System.out.println(" 添加用户：" + user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 赠送积分
	private void sendPoints(User user) {
		System.out.println("  发 送 积 分 给 指 定 用 户:" + user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UserServiceBlockQueue().register();
	}
}
