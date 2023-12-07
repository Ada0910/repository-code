package com.ada.software.design.observer.event;

/**
 *
 * <b><code></code></b>
 * <p/>  
 *
 * <p/>
 *
 * @Date: 2022/10/26 23:36
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MouseEventTest {
	public static void main(String[] args) {
		try {
			MouseEventCallback callback = new MouseEventCallback();
//注册事件
			Mouse mouse = new Mouse();
			mouse.addLisenter(MouseEventType.ON_CLICK, callback);
			mouse.addLisenter(MouseEventType.ON_MOVE, callback);
			mouse.addLisenter(MouseEventType.ON_WHEEL, callback);
			mouse.addLisenter(MouseEventType.ON_OVER, callback);
//调用方法
			mouse.click();
//失焦事件
			mouse.blur();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
