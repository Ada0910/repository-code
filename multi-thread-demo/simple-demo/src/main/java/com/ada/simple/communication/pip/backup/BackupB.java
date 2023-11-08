package com.ada.multi.thread.communication.pip.backup;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 17:17
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BackupB extends Thread {
	private DBTools tools;

	public BackupB(DBTools tools) {
		this.tools = tools;
	}

	@Override
	public void run() {
		tools.backupB();
	}
}
