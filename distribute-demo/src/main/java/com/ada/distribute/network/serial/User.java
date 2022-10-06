package com.ada.distribute.network.serial;

import java.io.Serializable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 用户实体类
 *
 * 如果不实现序列化的接口，则会报下面的错误
 * java.io.NotSerializableException: com.ada.distribute.network.serial.User
 *
 * <p/>
 *
 * @Date: 2022/10/6 18:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class User implements Serializable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
