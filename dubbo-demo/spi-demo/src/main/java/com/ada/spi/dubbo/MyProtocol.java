package com.ada.spi.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * 自定义协议类
 *
 * <p/>
 *
 * @Date: 2023/2/27 23:57
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyProtocol implements Protocol {
	@Override
	public int getDefaultPort() {
		return 8888;
	}

	@Override
	public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
		return null;
	}

	@Override
	public <T> Invoker<T> refer(Class<T> aClass, URL url) throws RpcException {
		return null;
	}

	@Override
	public void destroy() {

	}
}
