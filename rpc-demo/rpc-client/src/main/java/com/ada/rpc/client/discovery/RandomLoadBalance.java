package com.ada.rpc.client.discovery;

import java.util.List;
import java.util.Random;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/11/13 21:12
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RandomLoadBalance extends AbstractLoadBalance {


	@Override
	protected String doSelect(List<String> repos) {
		int length = repos.size();
		Random random = new Random(); //从repos的集合内容随机获得一个地址
		return repos.get(random.nextInt(length));
	}
}
