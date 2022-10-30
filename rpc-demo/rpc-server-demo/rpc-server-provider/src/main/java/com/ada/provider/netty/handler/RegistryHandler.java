package com.ada.provider.netty.handler;

import com.ada.api.netty.trans.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 扫描发布的服务所在的位置
 * <p/>
 *
 * @Date: 2022/10/30 18:47
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

	//用保存所有可用的服务<接口的名字，实例实现方法>
	public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();


	//保存所有相关的服务类
	private List<String> classNames = new ArrayList<String>();

	// 初始化
	public RegistryHandler() {
		scannerClass("com.ada.provider.netty.impl");
		doRegister();
	}


	/**
	 * 扫描
	 */
	private void scannerClass(String packageName) {
		// 获取编译后的路径
		URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
		File dir = new File(url.getFile());
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				scannerClass(packageName + "." + file.getName());
			} else {
				classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
			}
		}
	}


	/**
	 * 把扫描到的类加载注册到容器中（注册，对外提供的名字）
	 */
	private void doRegister() {
		if (classNames.size() == 0) {
			return;
		}
		for (String className : classNames) {
			try {
				Class<?> clazz = Class.forName(className);
				Class<?> i = clazz.getInterfaces()[0];
				registryMap.put(i.getName(), clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 有客户端连上的时候，就会触发，回调
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Object result = new Object();
		// 客户端连接过来之后，获取InvokerProtocol对象，并解析
		InvokerProtocol request = (InvokerProtocol) msg;

		// 找到容器里面已经注册好的服务
		if (registryMap.containsKey(request.getClassName())) {
			Object clazz = registryMap.get(request.getClassName());
			Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
			result = method.invoke(clazz, request.getValues());
		}
		//通过网络流写会客户端
		ctx.write(result);
		ctx.flush();
		ctx.close();
	}

	/**
	 * 连接发生异常就会调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
