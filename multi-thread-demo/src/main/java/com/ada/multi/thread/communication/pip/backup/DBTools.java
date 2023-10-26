package com.ada.multi.thread.communication.pip.backup;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 17:06
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class DBTools {
	volatile private boolean prevIsA = false;

	public synchronized void backupA() {
		try {
			while (prevIsA) {
				wait();
			}

			for (int i = 0; i < 5; i++) {
				System.out.println("AAAAA");
			}
			prevIsA = true;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public synchronized void backupB() {
		try {
			while (!prevIsA) {
				wait();
			}

			for (int i = 0; i < 5; i++) {
				System.out.println("BBBBB");
			}
			prevIsA = false;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
