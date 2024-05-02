package com.ada.simple.demo.pc.stack;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 12:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class P {
	private MyStack myStack;

	public P(MyStack myStack) {
		this.myStack = myStack;
	}

	public void pushService() {
		myStack.push();
	}
}
