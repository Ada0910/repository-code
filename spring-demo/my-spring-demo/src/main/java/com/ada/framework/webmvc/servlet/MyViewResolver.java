package com.ada.framework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 21:51
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyViewResolver {

	private final String DEFAULT_TEMPLATE_SUFFEX = "html";

	private File templateRootDir;
	private String viewName;

	public String getViewName() {
		return viewName;
	}

	public MyViewResolver(String templateRoot) {
		String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
		templateRootDir = new File(templateRootPath);
	}

	public MyView resolveViewName(String viewName, Locale locale) throws Exception {
		if (null == viewName || "".equals(viewName.trim())) {
			return null;
		}

		viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFEX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFEX);
		File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", ""));
		return new MyView(templateFile);
	}
}
