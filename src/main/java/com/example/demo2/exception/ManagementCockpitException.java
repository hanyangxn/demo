package com.example.demo2.exception;

public class ManagementCockpitException extends RuntimeException {

	//异常代码
	private String code;

	public ManagementCockpitException(String message) {
        super(message);
    }

	public ManagementCockpitException(String code, String message) {
        super(message);
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
