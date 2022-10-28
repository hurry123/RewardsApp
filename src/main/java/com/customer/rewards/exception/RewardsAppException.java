package com.customer.rewards.exception;

public class RewardsAppException extends Exception {

	private static final long serialVersionUID = 3456747997689977886L;
	private String code = null;
	private String message = "";

	public RewardsAppException(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
