package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:59
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PassportTest {
	public static void main(String[] args) {
		IPassportForThird passportForThird = new PassportForThirdAdapter();
		passportForThird.loginForQQ("");
	}
}
