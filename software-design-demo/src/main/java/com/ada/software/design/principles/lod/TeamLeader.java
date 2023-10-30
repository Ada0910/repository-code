package com.ada.software.design.principles.lod;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 小组长
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:07
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class TeamLeader {
	public void checkNumberOfCourses() {
		List<Course> courseList = new ArrayList<Course>();
		for (int i = 0; i < 20; i++) {
			courseList.add(new Course());
		}
		System.out.println("目前已发布的课程数量是：" + courseList.size());
	}
}
