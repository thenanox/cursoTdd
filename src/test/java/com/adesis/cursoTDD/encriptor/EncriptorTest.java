package com.adesis.cursoTDD.encriptor;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;

import com.adesis.cursoTDD.encriptor.strategy.EncryptCharStrategy;
import com.adesis.cursoTDD.encriptor.strategy.EncryptNumberStrategy;
import com.adesis.cursoTDD.encriptor.strategy.EncryptStrategy;
import com.adesis.cursoTDD.encriptor.validator.WordValidator;

public class EncriptorTest {

	private static final String DEFAULT_SENTENCE = "Hola Mundo";
	private static final String DEFAULT_CHARS = "hxlx";
	private static final String DEFAULT_WORD = "hola";
	private Encriptor encriptor = new Encriptor();
	private WordValidator validator = new WordValidator();
	private EncryptStrategy encryptStrategy;

	@Before
	public void setUp() {
		encryptStrategy = new EncryptCharStrategy();
		encriptor.setStrategy(encryptStrategy);
		encriptor.setValidator(validator);
	}
	
	

	@Test(expected = NullPointerException.class)
	public void checkNullToWord() {
		encriptor.cryptWord(null);
	}

	@Test
	public void checkEmptyWordToWord() {
		String result = encriptor.cryptWord("");
		assertThat(result).isEqualTo("");
	}

	@Test
	public void checkDefaultWordToWord() {
		String result = encriptor.cryptWord(DEFAULT_WORD);
		assertThat(result).isEqualTo("jqnc");
	}

	@Test(expected = InvalidParameterException.class)
	public void checkDefaultWordWithSpace() {
		encriptor.cryptWord(DEFAULT_WORD + " ");
	}

	@Test
	public void checkDefaultWordToNumber() {
		encryptStrategy = new EncryptNumberStrategy();
		encriptor.setStrategy(encryptStrategy);
		String result = encriptor.cryptWord(DEFAULT_WORD);
		assertThat(result).isEqualTo("10611311099");
	}

	@Test
	public void checkDefaultWordAndCharsToWord() {
		String result = encriptor.cryptWord(DEFAULT_WORD, DEFAULT_CHARS);
		assertThat(result).isEqualTo("jona");
	}

	@Test
	public void checkDefaulSentenceToWord() {
		String result = encriptor.cryptSentence(DEFAULT_SENTENCE);
		assertThat(result).isEqualTo("Jqnc\"Owpfq");
	}

	@Test
	public void checkGetWord() {
		String[] result = encriptor.getWords(DEFAULT_SENTENCE);
		String[] expected = { "Hola", "Mundo" };
		assertThat(result).isEqualTo(expected);
	}
	
	public String callArguments;
	
	@Test
	public void printToSystemOut() {
		Logger spyLogger = new Logger(){
			 @Override
			 public void log(String message){
				 callArguments = message;
			 }
		};
		Printer printer = new Printer(spyLogger);
		printer.print("hola");
		assertThat("<hola>").isEqualTo(callArguments);
	}
}
