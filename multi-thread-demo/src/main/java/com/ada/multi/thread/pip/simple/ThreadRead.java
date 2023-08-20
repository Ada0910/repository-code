package com.ada.multi.thread.pip.simple;

import java.io.PipedReader;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 16:33
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadRead extends Thread {

	private ReadData readData;

	private PipedReader input;

	public ThreadRead(ReadData readData, PipedReader input) {
		this.readData = readData;
		this.input = input;
	}

	@Override
	public void run() {
		try {
			readData.readMethod(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
