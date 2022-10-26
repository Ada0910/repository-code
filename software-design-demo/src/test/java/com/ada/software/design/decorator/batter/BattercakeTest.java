package com.ada.software.design.decorator.batter;

import com.ada.software.design.decorator.batter.BaseBattercake;
import com.ada.software.design.decorator.batter.Battercake;
import com.ada.software.design.decorator.batter.EggDecorator;
import com.ada.software.design.decorator.batter.SausageDecorator;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 20:05
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class BattercakeTest {
	public static void main(String[] args) {
		Battercake battercake;
//路边摊买一个煎饼
		battercake = new BaseBattercake();
//煎饼有点小，想再加一个鸡蛋
		battercake = new EggDecorator(battercake);
//再加一个鸡蛋
		battercake = new EggDecorator(battercake);
//很饿，再加根香肠

		battercake = new SausageDecorator(battercake);
//跟静态代理最大区别就是职责不同
//静态代理不一定要满足 is-a 的关系
//静态代理会做功能增强，同一个职责变得不一样
//装饰器更多考虑是扩展
		System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());
	}
}
