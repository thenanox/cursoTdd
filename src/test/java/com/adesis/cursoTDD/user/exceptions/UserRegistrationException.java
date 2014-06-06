package com.adesis.cursoTDD.user.exceptions;

public class UserRegistrationException extends RuntimeException {

	private static final long serialVersionUID = -5993889753520855469L;

	public UserRegistrationException(String message) {
		super(message);
	}
}
