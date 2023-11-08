package com.ada.software.design.chain;

/**
 *
 * <b><code></code></b>
 * <p/>
 * 一个准备列表
 * <p/>
 * <b>Creation Time:</b> 2023/6/28 16:40
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class PreparationList {

	/**
	 * 是否洗脸
	 */
	private boolean washFace;

	/**
	 * 是否洗头
	 */
	private boolean washHair;

	/**
	 * 是否吃早餐
	 */
	private boolean haveBreakfast;

	public boolean isWashFace() {
		return washFace;
	}

	public void setWashFace(boolean washFace) {
		this.washFace = washFace;
	}

	public boolean isWashHair() {
		return washHair;
	}

	public void setWashHair(boolean washHair) {
		this.washHair = washHair;
	}

	public boolean isHaveBreakfast() {
		return haveBreakfast;
	}

	public void setHaveBreakfast(boolean haveBreakfast) {
		this.haveBreakfast = haveBreakfast;
	}

	@Override
	public String toString() {
		return "ThingList [washFace=" + washFace + ", washHair=" + washHair + ", haveBreakfast=" + haveBreakfast + "]";
	}

}
