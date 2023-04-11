package com.ada.spring.servlet.v1;

import com.ada.spring.anno.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 自定义我的MyDispatcherServlet
 * <p/>
 *
 * @Date: 2023/4/9 12:44
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyDispatcherServlet extends HttpServlet {

	//配置文件
	private Properties contextConfig = new Properties();

	//保存所有保存到的类名
	private List<String> classNames = new ArrayList<String>();

	//IOC容器
	private Map<String, Object> ioc = new HashMap<String, Object>();

	//HandlerMapping 容器
	private Map<String, Method> handlerMapping = new HashMap<String, Method>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}

	/**
	 * 调用阶段
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//6.调用，运行阶段
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("500错误!," + Arrays.toString(e.getStackTrace()));
		}
	}


	/**
	 * 初始化流程
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 1.加载配置文件
		doConfig(config);


		//获取要扫描的路径
		String scanPackage = (String) contextConfig.get("scanPackage");
		// 2.扫描相关的类
		doScanner(scanPackage);

		//3.初始化扫描到的类并将他们放到IOC容器中
		doInstance();

		//4.完成依赖注入
		doAutowired();


		//5.初始化HandlerMapping
		initHandlerMapping();

		System.out.println("My Spring初始化完成！");
	}


	/**
	 * 加载配置文件
	 * @param config
	 */
	private void doConfig(ServletConfig config) {
		//从配置中获取参数(加载的web.xml中param-name对应的param-value)
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		//获取文件的配置文件流
		InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			//读取配置到properties中
			contextConfig.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					//关闭流
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通过配置文件扫描相关的类
	 * @param scanPackage
	 */
	private void doScanner(String scanPackage) {
		//scanPackage=com.ada.spring 将点转化成路径，类路径
		URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
		File classPath = new File(url.getFile());
		//迭代，这里可以看到相当于是class路径下的包，有各种各样的文件类型
		for (File file : classPath.listFiles()) {
			//文件类型，则继续递归
			if (file.isDirectory()) {
				doScanner(scanPackage + "." + file.getName());
			} else {

				//不是class 文件的，跳过
				if (!file.getName().endsWith(".class")) {
					continue;

				}

				//获取全路径名
				String className = scanPackage + "." + file.getName().replaceAll(".class", "");
				classNames.add(className);
			}
		}
	}

	/**
	 *  初始化扫描到的类并将他们放到IOC容器中
	 */
	private void doInstance() {
		//如果为空，则返回
		if (classNames.isEmpty()) {
			return;
		}
		try {
			//循环类名
			for (String className : classNames) {
				Class<?> clazz = Class.forName(className);

				if (!clazz.isAnnotationPresent(MyController.class) && !clazz.isAnnotationPresent(MyService.class)) {
					continue;
				}

				//什么样的类需要初始化- 加了注解的方法
				if (clazz.isAnnotationPresent(MyController.class)) {
					Object instance = clazz.newInstance();
					//key className 首字母小写
					String beanName = toLowerFirstCase(clazz.getSimpleName());
					ioc.put(beanName, instance);
				} else if (clazz.isAnnotationPresent(MyService.class)) {
					//自定义beanName
					MyService service = clazz.getAnnotation(MyService.class);
					String beanName = service.value();
					//值为空
					if ("".equals(beanName)) {
						//则用默认的值默认首字母小写
						beanName = toLowerFirstCase(clazz.getSimpleName());
					}
					ioc.put(beanName, clazz.newInstance());
					//	3.根据类型自动赋值
					for (Class<?> i : clazz.getInterfaces()) {
						if (ioc.containsKey(i.getName())) {
							throw new RuntimeException("这个类《" + i.getName() + "》已经存在！");
						}
						ioc.put(i.getName(), clazz.newInstance());
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();


		}

	}

	/**
	 * 首字母大写
	 */
	private String toLowerFirstCase(String simpleName) {
		char[] chars = simpleName.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	/**
	 * 依赖注入
	 */
	private void doAutowired() {
		if (ioc.isEmpty()) {
			return;
		}

		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			//Declared-所有的字段，包括private
			Field[] fields = entry.getClass().getDeclaredFields();
			for (Field field : fields) {
				//isAnnotationPresent看他上面有没有该类的注解
				if (!field.isAnnotationPresent(MyAutowired.class)) {
					continue;
				}

				MyAutowired autowired = field.getAnnotation(MyAutowired.class);
				String beanName = autowired.value().trim();

				//ioc 容器中没有这个名字，则用类型注入(初始化)
				if ("".equals(beanName)) {
					beanName = field.getType().getName();
				}

				//暴力访问
				field.setAccessible(true);

				try {
					//用反射机制，动态的给字段赋值
					field.set(entry.getValue(), ioc.get(beanName));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化HandlerMapping
	 */
	private void initHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}

		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
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

				handlerMapping.put(url, method);
				System.out.println("HandlerMapping：" + url + "," + method);
			}


		}
	}


	/**
	 * 调用，运行阶段
	 */
	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取请求的URL（绝对路径）
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

		//如果handlerMapping中不存在该URL,打印，然后返回
		if (!handlerMapping.containsKey(url)) {
			resp.getWriter().write("404");
			return;
		}

		Method method = this.handlerMapping.get(url);

		//获取bean的名字
		String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());

		//获取形参列表
		Class<?>[] parameterTypes = method.getParameterTypes();

		//实参数组
		Object[] paramValues = new Object[parameterTypes.length];

		Map<String, String[]> paramMap = req.getParameterMap();
		for (int i = 0; i < parameterTypes.length; i++) {
			Class parameterType = parameterTypes[i];
			if (parameterType == HttpServletRequest.class) {
				paramValues[i] = req;
				continue;
			} else if (parameterType == HttpServletResponse.class) {
				paramValues[i] = resp;
				continue;
			} else if (parameterType == String.class) {
				//投机取巧的方法

				MyRequestParam requestParam = (MyRequestParam) parameterType.getAnnotation(MyRequestParam.class);
				//if (paramMap.containsKey(requestParam.value())) {
					for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
						String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replace("\\s", "");
						paramValues[i] = value;
					}
				//}
			}

		}

		method.invoke(ioc.get(beanName), paramValues);
	}


}
