package com.ada.simple.communication.pip.simple;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedWriter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 13:50
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class WriteData {

	public void writeMethod(PipedWriter out) throws IOException {
		System.out.println("writeMethod: ");

		for (int i = 0; i < 300; i++) {
			String outData = "" + (i + 1);
			out.write(outData);
			System.out.println("outData:" + outData);
		}

		System.out.println();
	}
}
