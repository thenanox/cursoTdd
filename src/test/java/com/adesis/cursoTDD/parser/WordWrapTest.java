package com.adesis.cursoTDD.parser;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class WordWrapTest {

	@Test
	public void wrapNullWord() {
		String wrappedWord = wrap(null, 10);
		Assertions.assertThat(wrappedWord).isEmpty();
	}

	@Test
	public void wrapEmptyWord() {
		String wrappedWord = wrap("", 10);
		Assertions.assertThat(wrappedWord).isEmpty();
	}

	@Test
	public void wrapOneShortWord() {
		String wrappedWord = wrap("hola", 5);
		Assertions.assertThat(wrappedWord).isEqualTo("hola");
	}

	@Test
	public void wrapOneWordWithSameLength() {
		String wrappedWord = wrap("amigo", 5);
		Assertions.assertThat(wrappedWord).isEqualTo("amigo");
	}

	@Test
	public void wrapOneTooLongWordIntoTwoLines() {
		String wrappedWord = wrap("murcielago", 5);
		Assertions.assertThat(wrappedWord).isEqualTo("murci\nelago");
	}

	@Test
	public void wrapOneTooLongWordIntoThreeLines() {
		String wrappedWord = wrap("murcielago", 4);
		Assertions.assertThat(wrappedWord).isEqualTo("murc\niela\ngo");
	}

	@Test
	public void wrapOneTooLongWordIntoFourLines() {
		String wrappedWord = wrap("murcielago", 3);
		Assertions.assertThat(wrappedWord).isEqualTo("mur\ncie\nlag\no");
	}

	public String wrap(String paragraph, int length) {
		if (paragraph == null || paragraph.isEmpty()) {
			return "";
		}

		String wrappedWord = paragraph;
		if (paragraph.length() <= length) {
			return wrappedWord;
		} else {
			wrappedWord = paragraph.substring(0, length) + "\n"
					+ paragraph.substring(length, length * 2);

			if (wrappedWord.length() - 1 < paragraph.length()) {
				wrappedWord += "\n" + paragraph.substring(length * 2);
			}

			if (wrappedWord.length() - 2 <= paragraph.length()) {
				wrappedWord += "\n" + paragraph.substring(length * 3);
			}

		}

		return wrappedWord;
	}
}
