package com.ada.multi.thread.communication.pip.simple;

import java.io.PipedInputStream;
import java.io.PipedReader;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 13:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ReadData {
	public void readMethod(PipedReader in) throws Exception {
		System.out.println("readMethod：");
		char[] bytes = new char[20];
		int readLength = in.read(bytes);
		while (readLength != -1) {
			String newData = new String(bytes, 0, readLength);
			System.out.println(newData);
			readLength = in.read(bytes);
		}

		System.out.println();
		in.close();
	}
}
