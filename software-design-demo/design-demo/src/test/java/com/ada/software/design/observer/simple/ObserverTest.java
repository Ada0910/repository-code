package com.ada.software.design.observer.simple;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 23:27
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ObserverTest {
	public static void main(String[] args) {
		Forum forum = Forum.getInstance();
		Teacher tom = new Teacher("Tom");
		Teacher mic = new Teacher("Mic");
		forum.addObserver(tom);
		forum.addObserver(mic);
//业务逻辑代码
		Question question = new Question();
		question.setUserName("小明");
		question.setContent("观察者模式适用于哪些场景？");
		forum.publishQuestion(question);
	}
}
