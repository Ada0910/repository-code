package com.ada.simple.demo.pc.value;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2023/8/17 18:19
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class ThreadP extends Thread {

	private ProducerDemo producerDemo;

	public ThreadP(ProducerDemo producerDemo) {
		this.producerDemo = producerDemo;
	}

	@Override
	public void run() {
		while (true) {
			producerDemo.setValue();
		}


	}
}
