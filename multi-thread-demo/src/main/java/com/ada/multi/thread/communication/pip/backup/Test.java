package com.ada.multi.thread.communication.pip.backup;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2023/8/20 17:21
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Test {
	public static void main(String[] args) {
		DBTools tools = new DBTools();
		for (int i = 0; i < 20; i++) {
			BackupB output = new BackupB(tools);
			output.start();

			BackupA input  = new BackupA(tools);
			input.start();
		}
	}
}
