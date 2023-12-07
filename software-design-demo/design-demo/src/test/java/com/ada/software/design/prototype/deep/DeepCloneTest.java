package com.ada.software.design.prototype.deep;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 深拷贝测试类
 * <p/>
 *
 * @Date: 2022/9/18 16:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DeepCloneTest {
	public static void main(String[] args) {
		MonkeyKing monkeyKing = new MonkeyKing();
		try {
			MonkeyKing clone = (MonkeyKing)monkeyKing.clone();
			System.out.println("深克隆：" + (monkeyKing.goldHoop == clone.goldHoop));
		} catch (Exception e) {
			e.printStackTrace();
		}

		MonkeyKing q = new MonkeyKing();
		MonkeyKing n = q.shallowClone(q);
		System.out.println("浅克隆：" + (q.goldHoop == n.goldHoop));
	}
}
