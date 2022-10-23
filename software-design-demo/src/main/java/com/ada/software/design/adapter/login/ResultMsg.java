package com.ada.software.design.adapter.login;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 返回结果处理类
 *
 * <p/>
 *
 * @Date: 2022/10/23 16:45
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class ResultMsg {
	private int code;
	private String msg;
	private Object data;

	public ResultMsg(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
