package com.adesis.cursoTDD.encriptor;

public class Printer {
	private Logger logger;
	public Printer(Logger logger) {
		super();
		this.logger = logger;
	}

	public void print(String message) {
		logger.log("<"+message+">");
	}
}
