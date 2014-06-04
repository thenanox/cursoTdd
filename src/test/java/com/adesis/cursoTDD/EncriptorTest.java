package com.adesis.cursoTDD;

import static org.assertj.core.api.Assertions.*;
import java.security.InvalidParameterException;

import org.junit.Test;

public class EncriptorTest {

	private static final String DEFAULT_SENTENCE = "Hola Mundo";
	private static final String DEFAULT_CHARS = "hxlx";
	private static final String DEFAULT_WORD = "hola";
	private Encriptor encriptor = new Encriptor();
	
	@Test(expected=NullPointerException.class)
	public void checkNullToWord(){
		encriptor.cryptWord(null);
	}
	
	@Test
	public void checkEmptyWordToWord(){
		String result = encriptor.cryptWord("");
		assertThat(result).isEqualTo("");
	}
	
	@Test
	public void checkDefaultWordToWord(){
		String result = encriptor.cryptWord(DEFAULT_WORD);
		assertThat(result).isEqualTo("jqnc");
	}
	
	@Test(expected=InvalidParameterException.class)
	public void checkDefaultWordWithSpace(){
		encriptor.cryptWord(DEFAULT_WORD + " ");
	}
	
	@Test
	public void checkDefaultWordToNumber(){
		String result = encriptor.cryptWordToNumbers(DEFAULT_WORD);
		assertThat(result).isEqualTo("10611311099");
	}
	
	@Test
	public void checkDefaultWordAndCharsToWord(){
		String result = encriptor.cryptWord(DEFAULT_WORD, DEFAULT_CHARS);
		assertThat(result).isEqualTo("jona");
	}
	
	@Test
	public void checkDefaulSentenceToWord(){
		String result = encriptor.cryptSentence(DEFAULT_SENTENCE);
		assertThat(result).isEqualTo("Jqnc\"Owpfq");
	}
	
	@Test
	public void checkGetWord(){
		String[] result = encriptor.getWords(DEFAULT_SENTENCE);
		String[] expected = {"Hola","Mundo"};
		assertThat(result).isEqualTo(expected);
	}
}
