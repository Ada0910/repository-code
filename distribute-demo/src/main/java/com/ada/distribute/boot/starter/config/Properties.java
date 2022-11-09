package com.ada.distribute.boot.starter.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:50
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
@ConfigurationProperties(prefix = Properties.FORMAT_PREFIX)
public class Properties {
	public static final String FORMAT_PREFIX = "ada.format";

	private Map<String,Object> info;

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
}
