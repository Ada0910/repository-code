package com.ada.multi.thread.synchronize.syn;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * synchronized代码块间的同步性
 * <p/>
 *
 * @Date: 2023/8/7 23:39
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ObjcectServiceTest {
	public static void main(String[] args) {
		ObjectService service = new ObjectService();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("B");
	    b.start();
	}
}
