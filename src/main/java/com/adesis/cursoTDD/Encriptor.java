package com.adesis.cursoTDD;

import java.security.InvalidParameterException;

public class Encriptor {

	public String cryptWord(String word) {
		assertThatIsWord(word);
		return encrypt(word, true);
	}

	public String cryptWordToNumbers(String word) {
		assertThatIsWord(word);
		return encrypt(word, false);
	}

	public String cryptSentence(String sentence) {
		return encrypt(sentence, true);
	}
	
	private void assertThatIsWord(String word) {
		if (wordContainsSpace(word)) {
			throw new InvalidParameterException();
		}
	}

	private boolean wordContainsSpace(String word) {
		return word.contains(" ");
	}

	private String encrypt(String word, boolean isChar) {
		String newWord = "";
		for (int i = 0; i < word.length(); i++) {
			newWord += buildNewWord(isChar, word.charAt(i));
		}
		return newWord;
	}

	private String buildNewWord(boolean cryptAsChar, int valueToCrypt) {
		if (cryptAsChar) {
			return cryptStringAsChar(valueToCrypt);
		} else {
			return cryptStringAsNumber(valueToCrypt);
		}
	}

	private String cryptStringAsChar(int charValue) {
		return String.valueOf((char) (charValue + 2));
	}

	private String cryptStringAsNumber(int charValue) {
		return String.valueOf(charValue + 2);
	}

	public String cryptWord(String word, String charsToReplace) {
		assertThatIsWord(word);
		char[] result = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < charsToReplace.length(); j++) {
				if (charsToReplace.charAt(j) == word.charAt(i)) {
					int charValue = word.charAt(i);
					result[i] = (char) (charValue + 2);
				}
			}
		}
		return String.valueOf(result);
	}

	public String[] getWords(String sentence) {
		return sentence.split(" ");
	}

	public void printWords(String sentence) {
		String[] words = getWords(sentence);
		for (String word : words) {
			System.out.print("<" + word + ">");
		}
	}

}
