package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PassportForThirdAdapter extends SiginService implements IPassportForThird {
	@Override
	public ResultMsg loginForQQ(String id) {
		return processLogin(id, LoginForQQAdapter.class);
	}

	@Override
	public ResultMsg loginForWechat(String id) {
		return processLogin(id, LoginForWechatAdapter.class);
	}

	@Override
	public ResultMsg loginForToken(String token) {
		return processLogin(token, LoginForTokenAdapter.class);
	}

	@Override
	public ResultMsg loginForTelphone(String telphone, String code) {
		return processLogin(telphone, LoginForTelAdapter.class);
	}

	@Override
	public ResultMsg loginForRegist(String username, String passport) {
		super.regist(username, null);
		return super.login(username, null);
	}

	private ResultMsg processLogin(String key, Class<? extends LoginAdapter> clazz) {
		try {
			LoginAdapter adapter = clazz.newInstance();
			if (adapter.support(adapter)) {
				return adapter.login(key, adapter);
			} else {
				return null;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
