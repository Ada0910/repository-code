package com.ada.software.design.template;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 课程类测试方法
 * <p/>
 *
 * @Date: 2022/10/23 16:09
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class NetworkCourseTest {
	public static void main(String[] args) {
		System.out.println("---Java 架构师课程---");
		NetworkCourse javaCourse = new JavaCourse();
		javaCourse.createCourse();

		System.out.println("---大数据课程---");
		NetworkCourse bigDataCourse = new BigDataCourse(true);
		bigDataCourse.createCourse();
	}
}
