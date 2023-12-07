package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class SigninForThirdServiceTest {
	public static void main(String[] args) {
		SigninForThirdService service = new SigninForThirdService();
//不改变原来的代码，也要能够兼容新的需求
//还可以再加一层策略模式
		service.loginForQQ("sdfgdgfwresdf9123sdf");
	}
}
