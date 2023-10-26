package com.ada.multi.thread.communication.pip.simple;

import java.io.IOException;
import java.io.PipedWriter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 16:30
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ThreadWrite extends  Thread{

	private WriteData writeData;

	private PipedWriter out;

	public ThreadWrite(WriteData writeData, PipedWriter out) {
		this.writeData = writeData;
		this.out = out;
	}

	@Override
	public void run() {
		try {
			writeData.writeMethod(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
