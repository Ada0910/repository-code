package com.ada.software.design.observer.simple;

import java.util.Observable;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 23:24
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class Forum extends Observable {
	private String name = "论坛";
	private static Forum forum = null;
	private Forum(){}
	public static Forum getInstance(){
		if(null == forum){
			forum = new Forum();
		}
		return forum;
	}
	public String getName() {
		return name;
	}
	public void publishQuestion(Question question){
		System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题。");
		setChanged();
		notifyObservers(question);
	}
}
