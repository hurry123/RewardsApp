package com.customer.rewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK, reason = "No Data Found for the Given Customer Id")
public class RewardsAppNoDataException extends RewardsAppException {

	private static final long serialVersionUID = -3456345633764678976L;

	public RewardsAppNoDataException(String message, String code) {
		super(message, code);
	}

}
