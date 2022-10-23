package com.ada.software.design.template;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 模板会有一个或者多个未现实方法，
 * 而且这几个未实现方法有固定的执行循序
 *
 * <p/>
 *
 * @Date: 2022/10/23 15:56
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public abstract class NetworkCourse {


	protected final void createCourse() {
		//1、发布预习资料
		this.postPreResource();
		//2、制作 PPT 课件
		this.createPPT();
		//3、在线直播
		this.liveVideo();
		//4、提交课件、课堂笔记
		this.postNote();
		//5、提交源码
		this.postSource();

		//6、布置作业，有些课是没有作业，有些课是有作业的
		// 如果有作业的话，检查作业，如果没作业，完成了
		if (needHomework()) {
			checkHomework();
		}
	}

	abstract void checkHomework();

	//钩子方法：实现流程的微调
	protected boolean needHomework() {
		return false;
	}

	final void postSource() {
		System.out.println("提交源代码");
	}

	final void postNote() {
		System.out.println("提交课件和笔记");
	}

	final void liveVideo() {
		System.out.println("直播授课");
	}

	final void createPPT() {
		System.out.println("创建备课 PPT");
	}

	final void postPreResource() {
		System.out.println("分发预习资料");
	}
}
