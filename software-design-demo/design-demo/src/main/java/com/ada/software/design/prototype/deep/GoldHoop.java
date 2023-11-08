package com.ada.software.design.prototype.deep;

import java.io.Serializable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 金箍棒
 * <p/>
 *
 * @Date: 2022/9/18 16:12
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class GoldHoop implements Serializable {
	public float h = 100;
	public float d = 10;

	public void big() {
		this.d *= 2;
		this.h *= 2;
	}

	public void small() {
		this.d /= 2;
		this.h /= 2;
	}
}
