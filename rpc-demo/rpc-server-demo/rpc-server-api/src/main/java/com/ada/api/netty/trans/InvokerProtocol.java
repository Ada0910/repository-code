package com.ada.api.netty.trans;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 调用信息
 * <p/>
 *
 * @Date: 2022/10/30 18:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class InvokerProtocol implements Serializable {

	//类名
	private String className;
	//函数名称
	private String methodName;
	//形参列表
	private Class<?>[] parames;
	//实参列表
	private Object[] values;


	@Override
	public String toString() {
		return "InvokerProtocol{" +
				"className='" + className + '\'' +
				", methodName='" + methodName + '\'' +
				", parames=" + Arrays.toString(parames) +
				", values=" + Arrays.toString(values) +
				'}';
	}

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

	public Class<?>[] getParames() {
		return parames;
	}

	public void setParames(Class<?>[] parames) {
		this.parames = parames;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
}
