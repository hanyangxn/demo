package com.example.demo2.util;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {

	private static final Long serialVersionUID = 7098398557300885180L;

	private String code;
	private String msg;
	private T data;

	public ResultDto() {
		
	}

	public ResultDto(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * add by xlqi
	 * */
	@Override
	public String toString() {
		return "ResultDto{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
}
