package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:52
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface LoginAdapter {
	boolean support(Object adapter);

	ResultMsg login(String id, Object adapter);
}
