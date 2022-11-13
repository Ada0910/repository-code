package com.ada.provider.registry;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 服务注册名称和服务注册地址实现服务的管理
 * <p/>
 *
 * @Date: 2022/11/13 20:27
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface IRegistryCenter {
	void registry(String serviceName, String serviceAddress);
}
