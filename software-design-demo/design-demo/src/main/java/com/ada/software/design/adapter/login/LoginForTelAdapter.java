package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 手机登录适配器
 * <p/>
 *
 * @Date: 2022/10/23 16:54
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LoginForTelAdapter implements LoginAdapter {
	@Override
	public boolean support(Object adapter) {
		return adapter instanceof LoginForTelAdapter;
	}

	@Override
	public ResultMsg login(String id, Object adapter) {
		return null;
	}
}
