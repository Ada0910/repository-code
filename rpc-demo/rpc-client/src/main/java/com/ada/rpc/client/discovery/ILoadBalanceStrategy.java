package com.ada.rpc.client.discovery;

import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 负载平衡策略
 * <p/>
 *
 * @Date: 2022/11/13 21:11
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface ILoadBalanceStrategy {

	String selectHost(List<String> repos);
}
