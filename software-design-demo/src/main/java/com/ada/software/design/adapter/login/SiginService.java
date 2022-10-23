package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 老的登录逻辑
 * <p/>
 *
 * @Date: 2022/10/23 16:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SiginService {

	/**
	 * 注册方法
	 * @param username
	 * @param password
	 * @return
	 */
	public ResultMsg regist(String username, String password) {
		return new ResultMsg(200, "注册成功", new Member());
	}

	/**
	 * 登录的方法
	 * @param username
	 * @param password
	 * @return
	 */
	public ResultMsg login(String username, String password) {
		return null;
	}
}
