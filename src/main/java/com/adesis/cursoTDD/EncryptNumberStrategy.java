package com.adesis.cursoTDD;

public class EncryptNumberStrategy implements EncryptStrategy {

	@Override
	public String crypt(int charValue) {
		return String.valueOf(charValue + 2);
	}
}
