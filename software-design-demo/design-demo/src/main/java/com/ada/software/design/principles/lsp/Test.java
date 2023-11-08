package com.ada.software.design.principles.lsp;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:28
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class Test {
	public static void main(String[] args) {
		Rectangle rectangle = new Rectangle();
		rectangle.setWidth(20);
		rectangle.setHeight(10);
		resize(rectangle);

		//Square square = new Square();
		//square.setWidth(20);
		//square.setHeight(10);
		//resize(square);
	}

	public static void resize(Rectangle rectangle) {
		while (rectangle.getWidth() >= rectangle.getHeight()) {
			rectangle.setHeight(rectangle.getHeight() + 1);
			System.out.println("width:" + rectangle.getWidth() + ",height:" + rectangle.getHeight());
		}
		System.out.println("resize 方法结束" +
				"\nwidth:" + rectangle.getWidth() + ",height:" + rectangle.getHeight());
	}
}
