package com.ada.format.starter;


import com.ada.format.starter.config.Properties;
import com.ada.format.starter.format.IFormatProcessor;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 格式化模板
 * <p/>
 * <b>Creation Time:</b> 2022/11/9 15:21
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class FormatTemplate {

	private IFormatProcessor formatProcessor;

	private Properties properties ;

	public FormatTemplate(IFormatProcessor formatProcessor, Properties properties) {
		this.formatProcessor = formatProcessor;
		this.properties = properties;
	}

	/**
	 *  格式化
	 */
	public <T> String doFormat(T obj) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("执行格式化：").append("\n");
		stringBuilder.append("配置文件是:").append(formatProcessor.format(properties.getInfo())).append("<br/>");
		stringBuilder.append("对象格式化的结果是：").append(formatProcessor.format(obj)).append("\n");
		return stringBuilder.toString();
	}


}
