package com.ada.distributed.session2.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2024/4/23 0:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
//初始化Session配置
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
	public SessionInitializer() {
		super(SessionConfig.class);
	}
}