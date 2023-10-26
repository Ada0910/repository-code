package com.ada.multi.thread.keyword.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/7 23:43
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Task {
	public  void otherMethod(){
		System.out.println("执行-----Task----otherMethod方法");
	}

	public void doLongTimeTask(){
		synchronized (this){
			for (int i = 0; i < 1000; i++) {
				System.out.println("线程"+Thread.currentThread().getName() +"; i = "+ i);
			}
		}
	}
}
