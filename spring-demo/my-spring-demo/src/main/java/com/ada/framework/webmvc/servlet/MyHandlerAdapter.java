package com.ada.framework.webmvc.servlet;

import com.ada.spring.anno.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
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

	public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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

		//提取方法中的request和response参数
		Class<?>[] paramsTypes = handlerMapping.getMethod().getParameterTypes();
		for (int i = 0; i < paramsTypes.length; i++) {
			Class<?> type = paramsTypes[i];
			if (type == HttpServletRequest.class ||
					type == HttpServletResponse.class) {
				paramIndexMapping.put(type.getName(), i);
			}
		}


		//获得方法的形参列表
		Map<String, String[]> params = request.getParameterMap();

		//实参列表
		Object[] paramValues = new Object[paramsTypes.length];

		for (Map.Entry<String, String[]> parm : params.entrySet()) {
			String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]", "")
					.replaceAll("\\s", ",");

			if (!paramIndexMapping.containsKey(parm.getKey())) {
				continue;
			}

			int index = paramIndexMapping.get(parm.getKey());
			paramValues[index] = caseStringValue(value, paramsTypes[index]);
		}

		if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
			int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
			paramValues[reqIndex] = request;
		}

		if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
			int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
			paramValues[respIndex] = response;
		}

		Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
		if (result == null || result instanceof Void) {
			return null;
		}

		boolean isModelAndView = handlerMapping.getMethod().getReturnType() == MyModelAndView.class;
		if (isModelAndView) {
			return (MyModelAndView) result;
		}
		return null;
	}

	private Object caseStringValue(String value, Class<?> paramsType) {
		if (String.class == paramsType) {
			return value;
		}
		//如果是int
		if (Integer.class == paramsType) {
			return Integer.valueOf(value);
		} else if (Double.class == paramsType) {
			return Double.valueOf(value);
		} else {
			if (value != null) {
				return value;
			}
			return null;
		}
	}

}