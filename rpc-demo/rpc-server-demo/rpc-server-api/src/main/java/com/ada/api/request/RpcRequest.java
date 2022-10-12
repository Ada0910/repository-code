package com.ada.api.request;

import java.io.Serializable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/12 22:13
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RpcRequest implements Serializable {

	private String className;

	private String methodName;

	private Object[] parameters;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
