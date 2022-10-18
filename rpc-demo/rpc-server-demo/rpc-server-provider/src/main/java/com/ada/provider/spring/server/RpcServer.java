package com.ada.provider.spring.server;

import com.ada.provider.spring.anno.RpcService;
import com.ada.provider.spring.handler.ProcessorHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/15 0:06
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {
	// 线程池技术
	ExecutorService executorService = Executors.newCachedThreadPool();

	private Map<String, Object> handlerMap = new HashMap<>();

	private int port;

	public RpcServer(int port) {
		this.port = port;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				//每个socket是交个线程池来处理
				executorService.execute(new ProcessorHandler(socket, handlerMap));


			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
		if (!serviceBeanMap.isEmpty()) {
			for (Object serviceBean : serviceBeanMap.values()) {
				//拿到注解
				RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
				String serviceName = rpcService.value().getName();
				String version = rpcService.version();
				if (!StringUtils.isEmpty(version)) {
					serviceName += "-" + version;
					System.out.println("handlerMap的KEY的服务名是："+serviceName);
				}

				handlerMap.put(serviceName, serviceBean);
			}
		}
	}
}
