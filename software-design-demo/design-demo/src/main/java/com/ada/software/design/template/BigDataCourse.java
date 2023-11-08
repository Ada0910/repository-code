package com.ada.software.design.template;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 大数据课程类
 * <p/>
 *
 * @Date: 2022/10/23 16:07
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BigDataCourse extends NetworkCourse {
	private boolean needHomeworkFlag = false;

	public BigDataCourse(boolean needHomeworkFlag) {
		this.needHomeworkFlag = needHomeworkFlag;
	}

	@Override
	void checkHomework() {
		System.out.println("检查大数据的课后作业");
	}

	@Override
	protected boolean needHomework() {
		return this.needHomeworkFlag;
	}
}
