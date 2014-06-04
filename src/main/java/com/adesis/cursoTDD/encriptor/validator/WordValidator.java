package com.adesis.cursoTDD.encriptor.validator;

import java.security.InvalidParameterException;

public class WordValidator {
	public void assertThatIsWord(String word) {
		if (word.contains(" ")) {
			throw new InvalidParameterException();
		}
	}
}
