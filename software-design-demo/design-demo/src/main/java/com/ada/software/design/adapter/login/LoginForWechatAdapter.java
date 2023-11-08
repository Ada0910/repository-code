package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 微信登录适配器
 * <p/>
 *
 * @Date: 2022/10/23 16:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class LoginForWechatAdapter  implements  LoginAdapter{
	@Override
	public boolean support(Object adapter) {
		return adapter instanceof LoginForWechatAdapter;	}

	@Override
	public ResultMsg login(String id, Object adapter) {
		return null;
	}
}
