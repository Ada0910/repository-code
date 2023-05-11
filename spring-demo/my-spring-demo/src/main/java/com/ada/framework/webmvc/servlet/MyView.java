package com.ada.framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 22:02
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyView {

	public final String DEFAULT_CONTENT_TYPE = "text/html;charset= utf-8";

	private File viewFile;

	public MyView(File viewFile) {
		this.viewFile = viewFile;
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		RandomAccessFile ra = new RandomAccessFile(this.viewFile, "r");
		String line = null;
		while (null != (line = ra.readLine())) {
			line = new String(line.getBytes("ISO-8859-1"), "utf-8");
			Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				String paramName = matcher.group();
				paramName = paramName.replaceAll("￥\\{|\\}", "");
				Object paramValue = model.get(paramName);
				if (null == paramValue) {
					continue;
				}
				line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
				matcher = pattern.matcher(line);
			}
			sb.append(line);
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType(DEFAULT_CONTENT_TYPE);
		response.getWriter().write(sb.toString());
	}

	//处理特殊字符
	public static String makeStringForRegExp(String str) {
		return str.replace("\\", "\\\\").replace("*", "\\*")
				.replace("+", "\\+").replace("|", "\\|")
				.replace("{", "\\{").replace("}", "\\}")
				.replace("(", "\\(").replace(")", "\\)")
				.replace("^", "\\^").replace("$", "\\$")
				.replace("[", "\\[").replace("]", "\\]")
				.replace("?", "\\?").replace(",", "\\,")
				.replace(".", "\\.").replace("&", "\\&");
	}
}
