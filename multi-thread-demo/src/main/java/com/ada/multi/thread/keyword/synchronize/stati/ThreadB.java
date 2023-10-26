package com.ada.multi.thread.keyword.synchronize.stati;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/13 15:55
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadB  extends  Thread{
	@Override
	public void run() {
		Service.printB();
	}
}
