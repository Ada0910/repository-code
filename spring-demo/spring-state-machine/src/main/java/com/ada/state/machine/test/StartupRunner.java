package com.ada.state.machine.test;

import com.ada.state.machine.status.StatusEnum;
import com.ada.state.machine.status.StatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.statemachine.StateMachine;

import javax.annotation.Resource;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 测试类
 *
 * 实现这个CommandLineRunner接口，可以在初始化的时候做一些操作
 * <p/>
 *
 * @Date: 2024/7/30 23:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class StartupRunner implements CommandLineRunner {

	@Resource
	StateMachine<StatusEnum, StatusEvent> stateMachine;

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		stateMachine.sendEvent(StatusEvent.ONLINE);
		stateMachine.sendEvent(StatusEvent.PUBLISH);
		stateMachine.sendEvent(StatusEvent.ROLLBACK);
	}
}
