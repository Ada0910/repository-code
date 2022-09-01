package com.ada.software.design.principles.lod;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 测试类，老板想要知道上线了多少课程
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:09
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Test {
	public static void main(String[] args) {
		Boss boss = new Boss();
		boss.getCourseNum(new TeamLeader());

	}
}
