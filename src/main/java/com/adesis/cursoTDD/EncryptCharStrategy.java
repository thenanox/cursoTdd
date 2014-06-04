package com.adesis.cursoTDD;

public class EncryptCharStrategy implements EncryptStrategy {

	@Override
	public String crypt(int charValue) {
		return String.valueOf((char) (charValue + 2));
	}

}
