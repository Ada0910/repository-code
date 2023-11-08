package com.ada.software.design.observer.simple;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 23:26
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Teacher implements Observer {
	private String name;

	public Teacher(String name) {
		this.name = name;
	}

	@Override
	public void update(Observable o, Object arg) {
		Forum forum = (Forum) o;
		Question question = (Question)arg;
		System.out.println("===============================");
		System.out.println(name + "老师，你好！\n" +
						"您收到了一个来自“" + forum.getName() + "”的提问，希望您解答，问题内容如下：\n" +
						question.getContent() + "\n" +
				"提问者：" + question.getUserName());
	}
}
