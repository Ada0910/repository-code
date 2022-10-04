package com.ada.multi.thread.blockqueue.user;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 业务场景：
 * 就是用户注册的时候，在注册成功以后发放积分
 *
 * 改造前的代码
 * <p/>
 *
 * @Date: 2022/10/4 11:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class UserService {

	// 用户注册（注册之就有积分送）
	public boolean register() {
		User user = new User();
		user.setName("Mic");
		addUser(user);
		sendPoints(user);
		return true;
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
		new UserService().register();
	}
}
