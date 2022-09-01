package com.ada.software.design.principles.lsp;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 正方形类
 * <p/>
 * <b>Creation Time:</b> 2022/9/1 17:24
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class Square implements Quadrangle {
	private long length;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public long getWidth() {
		return getLength();
	}

	public void setWidth(long width) {
		setLength(width);
	}

	public long getHeight() {
		return getLength();
	}

	public void setHeight(long height) {
		setLength(height);
	}

}
