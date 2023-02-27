package com.ada.spi.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 自定义协议测试类（测试SPI扩展）
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

		//dubbo SPI
		Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
		System.out.println(protocol.getDefaultPort());
	}
}
