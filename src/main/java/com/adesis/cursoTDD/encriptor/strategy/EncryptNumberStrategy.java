package com.adesis.cursoTDD.encriptor.strategy;

public class EncryptNumberStrategy implements EncryptStrategy {

	@Override
	public String crypt(int charValue) {
		return String.valueOf(charValue + 2);
	}
}
