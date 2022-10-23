package com.ada.software.design.adapter.power;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:40
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class AdapterTest {
	public static void main(String[] args) {
		DirectCurrent directCurrent = new PowerAdapter(new AlternatCurrent());
		directCurrent.outputDc5V();
	}
}
