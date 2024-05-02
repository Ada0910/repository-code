package com.ada.simple.synchronize.stati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/13 15:54
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadA  extends  Thread{

	@Override
	public void run() {
		Service.printA();
	}
}
