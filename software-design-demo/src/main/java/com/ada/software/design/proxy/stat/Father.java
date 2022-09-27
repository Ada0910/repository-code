package com.ada.software.design.proxy.stat;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 父亲（代理对象）
 * <p/>
 * <b>Creation Time:</b> 2022/9/27 11:13
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Father {

	private Son son;

	public Father(Son son) {
		this.son = son;
	}

	//帮儿子找对象
	public void findLove() {
		System.out.println("父母物色对象");
		this.son.findLove();
		System.out.println("双方同意交往，确立关系");
	}
}
