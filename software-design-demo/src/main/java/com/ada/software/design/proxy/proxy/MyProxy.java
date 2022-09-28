package com.ada.software.design.proxy.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 11:06
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyProxy {
	public static final String ln = "\r\n";

	public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler h) {
		//1、动态生成源代码.java文件
		String src = generateSrc(interfaces);
		return null;
	}

	private static String generateSrc(Class<?>[] interfaces){
		StringBuffer sb = new StringBuffer();
		sb.append("package com.ada.software.design.proxy.proxy;" + ln);
		sb.append("import com.ada.software.design.proxy;" + ln);
		sb.append("import java.lang.reflect.*;" + ln);
		sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
		sb.append("MyInvocationHandler h;" + ln);
		sb.append("public $Proxy0(MyInvocationHandler h) { " + ln);
		sb.append("this.h = h;");
		sb.append("}" + ln);
		for (Method m : interfaces[0].getMethods()){
			Class<?>[] params = m.getParameterTypes();

			StringBuffer paramNames = new StringBuffer();
			StringBuffer paramValues = new StringBuffer();
			StringBuffer paramClasses = new StringBuffer();

			for (int i = 0; i < params.length; i++) {
				Class clazz = params[i];
				String type = clazz.getName();
				String paramName = toLowerFirstCase(clazz.getSimpleName());
				paramNames.append(type + " " +  paramName);
				paramValues.append(paramName);
				paramClasses.append(clazz.getName() + ".class");
				if(i > 0 && i < params.length-1){
					paramNames.append(",");
					paramClasses.append(",");
					paramValues.append(",");
				}
			}

			sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
			sb.append("try{" + ln);
			sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
			sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
			sb.append("}catch(Error _ex) { }");
			sb.append("catch(Throwable e){" + ln);
			sb.append("throw new UndeclaredThrowableException(e);" + ln);
			sb.append("}");
			sb.append(getReturnEmptyCode(m.getReturnType()));
			sb.append("}");
		}
		sb.append("}" + ln);
		return sb.toString();
	}

	private static Map<Class,Class> mappings = new HashMap<Class, Class>();
	static {
		mappings.put(int.class,Integer.class);
	}

	private static String getReturnEmptyCode(Class<?> returnClass){
		if(mappings.containsKey(returnClass)){
			return "return 0;";
		}else if(returnClass == void.class){
			return "";
		}else {
			return "return null;";
		}
	}

	private static String getCaseCode(String code,Class<?> returnClass){
		if(mappings.containsKey(returnClass)){
			return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
		}
		return code;
	}

	private static boolean hasReturnValue(Class<?> clazz){
		return clazz != void.class;
	}

	private static String toLowerFirstCase(String src){
		char [] chars = src.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
}
