package com.ada.software.design.adapter.power;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 适配器
 * <p/>
 *
 * @Date: 2022/10/23 16:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PowerAdapter implements DirectCurrent {
	private AlternatCurrent ac220;

	public PowerAdapter(AlternatCurrent ac220) {
		this.ac220 = ac220;
	}

	@Override
	public int outputDc5V() {
		int adapterInput = ac220.outputAC220V();
		//变压器...
		int adapterOutput = adapterInput / 44;
		System.out.println("使用 PowerAdapter 输入 AC:" + adapterInput + "V" + "输出 DC:" + adapterOutput + " V ");
		return adapterOutput;
	}
}
