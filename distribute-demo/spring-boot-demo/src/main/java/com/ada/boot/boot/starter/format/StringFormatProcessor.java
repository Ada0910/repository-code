package com.ada.boot.boot.starter.format;

import java.util.Objects;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 用String 实现
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:17
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class StringFormatProcessor  implements  IFormatProcessor{
	@Override
	public <T> String format(T obj) {
		return "StringFormatProcessor:"+ Objects.toString(obj);
	}
}
