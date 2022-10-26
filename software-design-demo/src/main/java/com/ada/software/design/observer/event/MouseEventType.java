package com.ada.software.design.observer.event;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 23:34
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public interface MouseEventType {
	//单击
	String ON_CLICK = "click";
	//双击
	String ON_DOUBLE_CLICK = "doubleClick";
	//弹起
	String ON_UP = "up";
	//按下
	String ON_DOWN = "down";
	//移动
	String ON_MOVE = "move";
	//滚动
	String ON_WHEEL = "wheel";
	//悬停
	String ON_OVER = "over";
	//失焦
	String ON_BLUR = "blur";
	//获焦
	String ON_FOCUS = "focus";
}
