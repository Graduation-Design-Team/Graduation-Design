package com.portal.utils;

import java.io.Serializable;

public class JsonData implements Serializable {
	private static final long serialVersionUID = 1L;

	//状态码,1表示成功，-1表示失败
	private Integer code;
	
	//结果
	private Object data;

	//错误描述
	private String msg;
	
	
	// 成功，传入数据
		public static JsonData buildSuccess() {
			return new JsonData(1, null, null);
		}

		// 成功，传入数据
		public static JsonData buildSuccess(Object data) {
			return new JsonData(1, data, null);
		}

		// 失败，传入描述信息
		public static JsonData buildError(String msg) {
			return new JsonData(-1, null, msg);
		}

		// 失败，传入描述信息,状态码
		public static JsonData buildError(String msg, Integer code) {
			return new JsonData(code, null, msg);
		}

		// 成功，传入数据,及描述信息
		public static JsonData buildSuccess(Object data, String msg) {
			return new JsonData(1, data, msg);
		}

		// 成功，传入数据,及状态码
		public static JsonData buildSuccess(Object data, int code) {
			return new JsonData(code, data, null);
		}
	
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonData(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public JsonData(int code, Object data,String msg) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
	
}
