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
public interface IPassportForThird {
	/**
	 * QQ 登录
	 * @param id
	 * @return
	 */
	ResultMsg loginForQQ(String id);

	/**
	 * 微信登录
	 * @param id
	 * @return
	 */
	ResultMsg loginForWechat(String id);

	/**
	 * 记住登录状态后自动登录
	 * @param token
	 * @return
	 */
	ResultMsg loginForToken(String token);

	/**
	 * 手机号登录
	 * @param telphone
	 * @param code
	 * @return
	 */
	ResultMsg loginForTelphone(String telphone, String code);

	/**
	 * 注册后自动登录
	 * @param username
	 * @param passport
	 * @return
	 */
	ResultMsg loginForRegist(String username, String passport);
}
