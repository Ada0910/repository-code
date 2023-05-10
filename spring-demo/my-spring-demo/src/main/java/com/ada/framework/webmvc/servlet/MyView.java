package com.ada.framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

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

	private File viewFile;

	public MyView(File viewFile) {
		this.viewFile = viewFile;
	}

	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

	}
}
