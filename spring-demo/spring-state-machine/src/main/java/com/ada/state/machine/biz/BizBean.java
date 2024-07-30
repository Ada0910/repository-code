package com.ada.state.machine.biz;

import com.ada.state.machine.status.StatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 业务对象
 * <p/>
 *
 * @Date: 2024/7/30 23:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@WithStateMachine
@Data
public class BizBean {
	private String status = StatusEnum.DRAFT.name();

	@OnTransition(target = "PUBLISH_TODO")
	public void online() {
		System.out.printf("操作上线，待发布. target status:{}", StatusEnum.PUBLISH_TODO.name());
		setStatus(StatusEnum.PUBLISH_TODO.name());
	}

	@OnTransition(target = "PUBLISH_DONE")
	public void publish() {
		System.out.printf("操作发布,发布完成. target status:{}", StatusEnum.PUBLISH_DONE.name());
		setStatus(StatusEnum.PUBLISH_DONE.name());
	}

	@OnTransition(target = "DRAFT")
	public void rollback() {
		System.out.printf("操作回滚,回到草稿状态. target status:{}", StatusEnum.DRAFT.name());
		setStatus(StatusEnum.DRAFT.name());
	}
}
