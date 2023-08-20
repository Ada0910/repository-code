package com.ada.multi.thread.pc.stack;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 12:53
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class C {
	MyStack myStack;

	public C(MyStack myStack) {
		this.myStack = myStack;
	}

	public  void popService(){
		System.out.println("类C经过popService 的函数处理后，list弹出来的值是：："+ myStack.pop());
	}
}
