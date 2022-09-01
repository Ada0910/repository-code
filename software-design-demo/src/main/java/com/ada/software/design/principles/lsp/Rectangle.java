package com.ada.software.design.principles.lsp;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 长方形父类
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:23
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Rectangle implements Quadrangle {
	private long height;
	private long width;


	public long getWidth() {
		return width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long length) {
		this.height = length;
	}

	public void setWidth(long width) {
		this.width = width;
	}
}
