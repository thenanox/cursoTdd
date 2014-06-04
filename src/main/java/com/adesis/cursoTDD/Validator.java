package com.adesis.cursoTDD;

import java.security.InvalidParameterException;

public class Validator {
	public void assertThatIsWord(String word) {
		if (word.contains(" ")) {
			throw new InvalidParameterException();
		}
	}
}
