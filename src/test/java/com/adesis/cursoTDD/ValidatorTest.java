package com.adesis.cursoTDD;

import java.security.InvalidParameterException;

import org.junit.Test;

public class ValidatorTest {

	private static final String WORD = "hola";
	private Validator validator = new Validator();
	
	@Test
	public void wordWithoutSpaceIsOk(){
		validator.assertThatIsWord(WORD);
	}

	@Test(expected=InvalidParameterException.class)
	public void wordWitSpaceIsNotOk(){
		validator.assertThatIsWord(WORD + " ");
	}
}
