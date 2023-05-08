package com.ada.framework.webmvc.servlet;

import com.ada.spring.anno.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/3 21:19
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyHandlerAdapter {

	public boolean supports(Object handler) {
		return handler instanceof MyHandlerMapping;
	}

	public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		MyHandlerMapping handlerMapping = (MyHandlerMapping) handler;
		//把方法的形参列表和request的参数列表所在的顺序进行一对一对应
		Map<String, Integer> paramIndexMapping = new HashMap<>();
		Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
		for (int i = 0; i < pa.length; i++) {
			for (Annotation a : pa[i]) {
				if (a instanceof MyRequestParam) {
					String paramName = ((MyRequestParam) a).value();
					if (!"".equals(paramName.trim())) {
						paramIndexMapping.put(paramName, i);
					}
				}
			}
		}
		return null;
	}
}
