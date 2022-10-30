package com.ada.provider.spring.handler;

import com.ada.api.simple.request.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/10 23:54
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ProcessorHandler implements Runnable {
	private Socket socket;
	private Map<String, Object> handlerMap;

	public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
		this.socket = socket;
		this.handlerMap = handlerMap;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * 需要处理流的问题
	 */
	@Override
	public void run() {
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			// 输入流有什么东西-请求参数，方法名称、参数
			RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
			Object result = invoke(rpcRequest);

			// 输出
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(result);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 反射调用
	 */
	private Object invoke(RpcRequest request) throws Exception {
		String serviceName = request.getClassName();
		if ("java.lang.Object".equalsIgnoreCase(serviceName)) {
			return null;
		}
		String version = request.getVersion();
		if (!StringUtils.isEmpty(version)) {
			serviceName += "-" + version;
		}
		Object service = handlerMap.get(serviceName);
		if (service == null) {
			// throw new RuntimeException("服务类找不到：" + serviceName + ":");
			// System.out.println("服务类找不到：" + serviceName );
		}

		// 获取实参
		Object[] parameters = request.getParameters();

		if (parameters != null) {

			// 实例化参数
			Class<?>[] types = new Class[parameters.length];

			for (int i = 0; i < parameters.length; i++) {
				types[i] = parameters[i].getClass();
			}

			// 实例化
			Class<?> clazz = Class.forName(request.getClassName());
			// 反射调用方法
			Method method = clazz.getMethod(request.getMethodName(), types);
			// 调用,调用的是一个已经实例化的对象
			Object result = method.invoke(service, parameters);
			return result;
		}
		//跟去请求的类进行加载
		Class clazz = Class.forName(request.getClassName());
		//sayHello, saveUser找到这个类中的方法
		Method method = clazz.getMethod(request.getMethodName());
		//HelloServiceImpl 进行反射调用
		Object result = method.invoke(service, parameters);
		return result;
	}


}
