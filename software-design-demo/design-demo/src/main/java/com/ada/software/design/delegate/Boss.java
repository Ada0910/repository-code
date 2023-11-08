package com.ada.software.design.delegate;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 老板类
 * <p/>
 *
 * @Date: 2022/10/8 22:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Boss {
	public void command(String command, Leader leader) {
		leader.doing(command);
	}
}
