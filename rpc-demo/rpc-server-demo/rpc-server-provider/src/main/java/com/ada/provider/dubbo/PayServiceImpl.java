package com.ada.provider.dubbo;

import com.ada.api.dubbo.IPayService;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 支付接口实现类
 * <p/>
 *
 * @Date: 2022/11/19 14:05
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class PayServiceImpl implements IPayService {
	@Override
	public String pay(String info) {
		System.out.println("调用PayServiceImpl的 pay方法：打印信息info信息：" + info);
		return "Dubbo 输出：:" + info;
	}
}
