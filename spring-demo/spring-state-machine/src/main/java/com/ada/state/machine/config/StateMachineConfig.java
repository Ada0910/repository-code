package com.ada.state.machine.config;

import com.ada.state.machine.status.StatusEnum;
import com.ada.state.machine.status.StatusEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2024/7/30 23:28
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StatusEnum, StatusEvent> {
	@Override
	public void configure(StateMachineStateConfigurer<StatusEnum, StatusEvent> states) throws Exception {
		states.withStates().initial(StatusEnum.DRAFT).states(EnumSet.allOf(StatusEnum.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<StatusEnum, StatusEvent> transitions) throws Exception {
		transitions.withExternal().source(StatusEnum.DRAFT).target(StatusEnum.PUBLISH_TODO).event(StatusEvent.ONLINE).and().withExternal().source(StatusEnum.PUBLISH_TODO).target(StatusEnum.PUBLISH_DONE).event(StatusEvent.PUBLISH).and().withExternal().source(StatusEnum.PUBLISH_DONE).target(StatusEnum.DRAFT).event(StatusEvent.ROLLBACK);
	}
}
