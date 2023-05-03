package com.ada.framework.beans;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/1 18:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyBeanWrapper {

	private Object wrappedInstance;

	private Class<?> wrappedClass;

	public MyBeanWrapper(Object wrappedInstance) {
		this.wrappedInstance = wrappedInstance;
	}

	/**
	 * Return the bean instance wrapped by this object.
	 */
	public Object getWrappedInstance() {
		return this.wrappedInstance;
	}

	/**
	 * Return the type of the wrapped bean instance.
	 */
	public Class<?> getWrappedClass() {
		return this.wrappedInstance.getClass();
	}
}
