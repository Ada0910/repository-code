package com.ada.framework.beans.support;

import com.ada.framework.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/5/1 17:18
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyBeanDefinitionReader {

	private List<String> registerBeanClasses = new ArrayList<>();

	private Properties config = new Properties();

	private final String SCAN_PACKAGE = "scanPackage";

	/**
	 * 构造方法，根据资源路径读取加载配置文件内容
	 */
	public MyBeanDefinitionReader(String... locations) {
		//通过URL定位，找到其对应的文件，然后转化为文件流
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath", ""));
		try {
			config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		doScanner(config.getProperty(SCAN_PACKAGE));
	}

	/**
	 * 扫描包路径获取文件流
	 * @param scanPackage 被扫描包的key
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
				registerBeanClasses.add(className);
			}
		}
	}


	/**
	 * 提供一个获取全局配置的方法
	 */
	public Properties getConfig() {
		return this.config;
	}


	/**
	 * 把配置文件中的扫描到的所有配置信息转换为BeanDefinition
	 */
	public List<MyBeanDefinition> loadBeanDefinitions() {
		List<MyBeanDefinition> res = new ArrayList<>();
		for (String className : registerBeanClasses) {
			MyBeanDefinition beanDefinition = doCreateBeanDefinition(className);
			if (beanDefinition == null) {
				continue;
			}
			res.add(beanDefinition);
		}

		return res;
	}

	/**
	 * 根据className来创建beandefinition
	 */
	private MyBeanDefinition doCreateBeanDefinition(String className) {
		try {
			Class<?> beanClass = Class.forName(className);

			//有可能是个接口，接口是不能实例化的，如果是接口，则用他的实现类来做beanClassName
			if (beanClass.isInterface()) {
				return null;
			}

			MyBeanDefinition beanDefinition = new MyBeanDefinition();
			beanDefinition.setBeanClassName(className);
			beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
			return beanDefinition;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
