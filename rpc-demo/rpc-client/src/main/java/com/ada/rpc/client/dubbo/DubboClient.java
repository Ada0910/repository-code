package com.ada.rpc.client.dubbo;

import com.ada.api.dubbo.IPayService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 调用dubbo服务的客户端
 * <p/>
 *
 * @Date: 2022/11/19 14:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DubboClient {
	public static void main(String[] args) {
		// 获取application.xml的内容
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"application.xml"});
		IPayService payService = (IPayService) classPathXmlApplicationContext.getBean("payService");
		System.out.println(payService.pay("hi,Dubbo"));
	}
}
