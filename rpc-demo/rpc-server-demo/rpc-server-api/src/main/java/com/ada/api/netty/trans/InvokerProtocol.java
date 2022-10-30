package com.ada.api.netty.trans;

import lombok.Data;

import java.io.Serializable;

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
@Data
public class InvokerProtocol implements Serializable {

	//类名
	private String className;
	//函数名称
	private String methodName;
	//形参列表
	private Class<?>[] parames;
	//实参列表
	private Object[] values;
}
