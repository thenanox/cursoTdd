package com.adesis.cursoTDD;

import java.security.InvalidParameterException;

import org.junit.Assert;
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
		Assert.assertEquals("", result);
	}
	
	@Test
	public void checkDefaultWordToWord(){
		String result = encriptor.cryptWord(DEFAULT_WORD);
		Assert.assertEquals("jqnc", result);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void checkDefaultWordWithSpace(){
		encriptor.cryptWord(DEFAULT_WORD + " ");
	}
	
	@Test
	public void checkDefaultWordToNumber(){
		String result = encriptor.cryptWordToNumbers(DEFAULT_WORD);
		Assert.assertEquals("10611311099", result);
	}
	
	@Test
	public void checkDefaultWordAndCharsToWord(){
		String result = encriptor.cryptWord(DEFAULT_WORD, DEFAULT_CHARS);
		Assert.assertEquals("jona", result);
	}
	
	@Test
	public void checkDefaulSentenceToWord(){
		String result = encriptor.cryptSentence(DEFAULT_SENTENCE);
		Assert.assertEquals("Jqnc\"Owpfq", result);
	}
	
	@Test
	public void checkGetWord(){
		String[] result = encriptor.getWords(DEFAULT_SENTENCE);
		String[] expected = {"Hola","Mundo"};
		Assert.assertArrayEquals(expected, result);
	}
}
