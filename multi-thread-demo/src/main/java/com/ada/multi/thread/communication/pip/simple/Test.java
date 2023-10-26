package com.ada.multi.thread.communication.pip.simple;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 16:42
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		WriteData writeData = new WriteData();
		ReadData readData = new ReadData();

		PipedReader pipedReader = new PipedReader();
		PipedWriter pipedWriter = new PipedWriter();

		pipedWriter.connect(pipedReader);

		ThreadRead threadRead = new ThreadRead(readData,pipedReader);
		threadRead.start();

		Thread.sleep(1000);

		ThreadWrite threadWrite = new ThreadWrite(writeData,pipedWriter);
		threadWrite.start();
	}
}
