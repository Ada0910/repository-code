package com.ada.software.design.proxy.proxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 手写的classloader
 * <p/>
 * <b>Creation Time:</b> 2022/9/28 11:04
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class MyClassLoader extends ClassLoader {
	private File classPathFile;

	public MyClassLoader() {
		String classPath = MyClassLoader.class.getResource("").getPath();
		this.classPathFile = new File(classPath);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String className = MyClassLoader.class.getPackage().getName() + "." + name;
		if (className != null) {
			File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
			if (classFile.exists()) {
				FileInputStream in = null;
				ByteArrayOutputStream out = null;
				try {
					in = new FileInputStream(classFile);
					out = new ByteArrayOutputStream();
					byte[] buff = new byte[1024];
					int len;
					while ((len = in.read(buff)) != -1) {
						out.write(buff, 0, len);
					}
					return defineClass(className, out.toByteArray(), 0, out.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
