package com.ada.rpc.client.discovery;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/13 20:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface IServerDiscovery {

	/**
	 * 根据服务名称返回服务地址
	 */
	String discovery(String serviceName);
}
