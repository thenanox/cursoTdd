package com.adesis.cursoTDD.encriptor.validator;

import java.security.InvalidParameterException;

import org.junit.Test;

import com.adesis.cursoTDD.encriptor.validator.WordValidator;

public class ValidatorTest {

	private static final String WORD = "hola";
	private WordValidator validator = new WordValidator();

	@Test
	public void wordWithoutSpaceIsOk() {
		validator.assertThatIsWord(WORD);
	}

	@Test(expected = InvalidParameterException.class)
	public void wordWitSpaceIsNotOk() {
		validator.assertThatIsWord(WORD + " ");
	}
}
