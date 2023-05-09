package com.ada.framework.webmvc;

import com.ada.framework.context.MyApplicationContext;
import com.ada.framework.webmvc.servlet.*;
import com.ada.spring.anno.MyController;
import com.ada.spring.anno.MyRequestMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * Spring mvc的入口
 * <p/>
 *
 * @Date: 2023/5/3 19:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Slf4j
public class MyDispatcherServlet extends HttpServlet {
	private MyApplicationContext context;

	private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

	private List<MyHandlerMapping> handlerMappings;

	private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapters = new HashMap<>();

	private List<MyViewResolver> viewResolvers = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			this.doDispatch(req, resp);
		} catch (Exception e) {
			resp.getWriter().write("500");
			System.out.println("500");
		}
	}

	/**
	 * 分发
	 */
	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1.通过从request中拿到一个URL，去匹配一个HandlerMapping
		MyHandlerMapping handler = getHandler(req);
		if (handler == null) {
			//404
			return;
		}

		//2.准备调用钱的参数
		MyHandlerAdapter adapter = getHandlerAdapter(handler);

		//	3.真正的调用方法
		MyModelAndView mv = adapter.handle(req, resp, handler);

		processDispatchResult(req, resp, mv);

	}

	/**
	 * 变成一个HTML
	 */
	private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, MyModelAndView mv) throws Exception {
		if (null == mv) {
			return;
		}

		//如果modelAndView不为null，则该如何处理
		if (this.viewResolvers.isEmpty()) {
			return;
		}

		for (MyViewResolver viewResolver : this.viewResolvers) {
			MyView view = viewResolver.resolveViewName(mv.getViewName(), null);
			view.render(mv.getModel(), req, resp);
		}
	}

	private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
		if (this.handlerAdapters.isEmpty()) {
			return null;
		}

		MyHandlerAdapter ha = this.handlerAdapters.get(handler);
		if (ha.supports(handler)) {
			return ha;
		}

		return null;
	}

	@Override
	public void init() throws ServletException {
		//1.初始化ApplicationContext
		context = new MyApplicationContext(CONTEXT_CONFIG_LOCATION);
		//2.初始化Spring mvc的九大组件
		initStrategies(context);
	}

	/**
	 * 初始化九大组件
	 */
	protected void initStrategies(MyApplicationContext context) {
		initMultipartResolver(context);
		initLocaleResolver(context);
		initThemeResolver(context);
		initHandlerMappings(context);
		initHandlerAdapters(context);
		initHandlerExceptionResolvers(context);
		initRequestToViewNameTranslator(context);
		initViewResolvers(context);
		initFlashMapManager(context);
	}


	private void initFlashMapManager(MyApplicationContext context) {
	}


	private void initViewResolvers(MyApplicationContext context) {
		//拿到一个模板的存放目录
		String templateRoot = context.getConfig().getProperty("templateRoot");
		String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
		File templateRootDir = new File(templateRootPath);

		for (String template : templateRootDir.list()) {
			this.viewResolvers.add(new MyViewResolver(templateRoot));
		}
	}

	private void initRequestToViewNameTranslator(MyApplicationContext context) {

	}

	private void initHandlerExceptionResolvers(MyApplicationContext context) {

	}

	/**
	 * 把一个request的请求变成一个handler
	 */
	private void initHandlerAdapters(MyApplicationContext context) {
		for (MyHandlerMapping handlerMapping : this.handlerMappings) {
			this.handlerAdapters.put(handlerMapping, new MyHandlerAdapter());
		}

	}

	/**
	 * 主要处理controller的URL和对应method的关系
	 */
	private void initHandlerMappings(MyApplicationContext context) {
		String[] beanNames = context.getBeanDefinitionNames();
		try {
			for (String beanName : beanNames) {
				Object controller = context.getBean(beanName);
				Class<?> clazz = controller.getClass();

				//如果没有controller的注解，则跳过
				if (!clazz.isAnnotationPresent(MyController.class)) {
					continue;
				}


				String baseUrl = "";
				//被controller修饰
				if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
					MyRequestMapping myRequestMapping = clazz.getAnnotation(MyRequestMapping.class);
					//即注解上的URL
					baseUrl = myRequestMapping.value();
				}

				for (Method method : clazz.getMethods()) {
					//如果没有被MyRequestMapping修饰，则取反
					if (!method.isAnnotationPresent(MyRequestMapping.class)) {
						continue;
					}

					MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
					String url = (baseUrl + "/" + annotation.value()).replaceAll("/+", "/");

					Pattern pattern = Pattern.compile(url);

					handlerMappings.add(new MyHandlerMapping(pattern, url, method));
					System.out.println("HandlerMapping：" + url + "," + method);
				}


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void initThemeResolver(MyApplicationContext context) {
	}

	private void initLocaleResolver(MyApplicationContext context) {
	}

	private void initMultipartResolver(MyApplicationContext context) {
	}


	private MyHandlerMapping getHandler(HttpServletRequest request) throws Exception {
		if (handlerMappings.isEmpty()) {
			return null;
		}

		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		url = url.replace(contextPath, "").replaceAll("/+", "/");

		for (MyHandlerMapping handler : handlerMappings) {
			try {
				Matcher matcher = handler.getPattern().matcher(url);
				if (!matcher.matches()) {
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}


}
