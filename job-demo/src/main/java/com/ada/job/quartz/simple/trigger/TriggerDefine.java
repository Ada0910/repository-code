package com.ada.job.quartz.simple.trigger;

import org.quartz.*;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 *
 *
 * <p/>
 * <b>Creation Time:</b> 2023/2/6 16:24
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class TriggerDefine {
	public static void main(String[] args) {

		/**
		 *  每两秒执行一次
		 */
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(2)
						.repeatForever())
				.build();


		/**
		 *  触发器二：
		 *
		 */
		Trigger dailyTimeIntervalTrigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
						.startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0)) //第天9：00开始
						.endingDailyAt(TimeOfDay.hourAndMinuteOfDay(16, 0)) //16：00 结束
						.onDaysOfTheWeek(1,2,3,4,5) //周一至周五执行
						.withIntervalInHours(1) //每间隔1小时执行一次
						.withRepeatCount(100))//最多重复100次（实际执行100+1次）
				.build();

	}
}
