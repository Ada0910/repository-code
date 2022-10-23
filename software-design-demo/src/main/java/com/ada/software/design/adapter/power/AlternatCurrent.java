package com.ada.software.design.adapter.power;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 交流电220V
 * <p/>
 *
 * @Date: 2022/10/23 16:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class AlternatCurrent {
	public int outputAC220V() {
		int output = 220;
		System.out.println("输出交流电" + output + "V");
		return output;
	}
}
