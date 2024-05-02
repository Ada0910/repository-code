package com.ada.simple.synchronize.obj;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 证明线程锁的是对象
 * <p/>
 *
 * @Date: 2023/8/6 18:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyObjectTest {
	public static void main(String[] args) {
		// 下面的代码是测试是证明线程锁的对象
		//MyObject object = new MyObject();
		//ThreadA a = new ThreadA(object);
		//a.setName("A");
		//ThreadB b = new ThreadB(object);
		////注释放开就是不同对象
		////MyObject object2 = new MyObject();
		////ThreadB b = new ThreadB(object2);
		//b.setName("B");
		//a.start();
		//b.start();


		//进一步验证
		MyObject object = new MyObject();
		ThreadA a = new ThreadA(object);
		a.setName("A");
		ThreadC b = new ThreadC(object);
		//注释放开就是不同对象
		//MyObject object2 = new MyObject();
		//ThreadB b = new ThreadB(object2);
		b.setName("B");
		a.start();
		b.start();
	}
}
