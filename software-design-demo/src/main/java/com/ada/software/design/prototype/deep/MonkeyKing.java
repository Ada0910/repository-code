package com.ada.software.design.prototype.deep;

import java.io.*;
import java.util.Date;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 齐天大圣类
 * <p/>
 *
 * @Date: 2022/9/18 16:14
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MonkeyKing extends Monkey implements Cloneable, Serializable {


	public GoldHoop goldHoop;

	/**
	 * 构造方法
	 */
	public MonkeyKing() {
		//只是初始化
		this.birthday = new Date();
		this.goldHoop = new GoldHoop();
	}

	@Override
	protected Object clone() {
		return this.deepClone();
	}

	/**
	 * 深拷贝
	 */
	public Object deepClone() {
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);

			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);

			MonkeyKing copy = (MonkeyKing) ois.readObject();
			copy.birthday = new Date();
			return copy;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *浅克隆
	 */
	public MonkeyKing shallowClone(MonkeyKing target) {

		MonkeyKing monkeyKing = new MonkeyKing();
		monkeyKing.height = target.height;
		monkeyKing.weight = target.height;

		monkeyKing.goldHoop = target.goldHoop;
		monkeyKing.birthday = new Date();
		return monkeyKing;
	}
}
