package com.ada.format.starter.format;

import com.alibaba.fastjson.JSON;

/**
 *
 * <b><code></code></b>
 * <p/>
 * JSON实现
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:18
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class JsonFormatProcessor implements IFormatProcessor {
	@Override
	public <T> String format(T obj) {
		return "JsonFormatProcessor:"+ JSON.toJSONString(obj);
	}
}
