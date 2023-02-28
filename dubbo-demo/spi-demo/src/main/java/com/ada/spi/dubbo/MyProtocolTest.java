package com.ada.spi.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Protocol;

import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 自定义协议扩展点测试类（测试SPI扩展）
 *
 * 扩展路径是：META-INF/dubbo/org.apache.dubbo.rpc.Protocol
 *
 * <p/>
 *
 * @Date: 2023/2/28 0:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyProtocolTest {
	public static void main(String[] args) {

		//dubbo SPI（自定义协议扩展点），源码的入口可以从这里看
		Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
		System.out.println(protocol.getDefaultPort());

		Compiler protocol2 = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();

		URL url = new URL("", "", 0);
		url = url.addParameter("cache", "cache");
		List<Filter> list = ExtensionLoader.getExtensionLoader(Filter.class).getActivateExtension(url, "cache");
		System.out.println(list.size());
	}
}
