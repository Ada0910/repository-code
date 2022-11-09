package com.ada.distribute.boot.starter.format;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 定义一个格式化的方法
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:12
 * @author xiewn
 * @version 1.0.0.1
 *
 */
public interface IFormatProcessor {
	<T> String format(T obj);
}
