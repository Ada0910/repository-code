package com.ada.rpc.client.discovery;

import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/13 21:18
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class AbstractLoadBalance implements ILoadBalanceStrategy {
	@Override
	public String selectHost(List<String> repos) {
		//repos可能为空， 可能只有一个。
		if (repos == null || repos.size() == 0) {
			return null;
		}
		if (repos.size() == 1) {
			return repos.get(0);
		}
		return doSelect(repos);
	}

	protected abstract String doSelect(List<String> repos);
}
