package com.example.demo2.util;

/**
 * @author xlqi
 * check 参数异常
 */
public class ParamChekErrorException extends RuntimeException {

	/**
	 * 参数列
	 */
	private String field;


	public ParamChekErrorException(String field, String msg) {
		super(msg);
		this.field = field;
	}

	public ParamChekErrorException(String msg) {
		super(msg);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
