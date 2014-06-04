package com.adesis.cursoTDD;

import java.security.InvalidParameterException;

public class Encriptor {

	public String cryptWord(String word) {
		assertThatIsWord(word);
		return encript(word, true);
	}

	public String cryptWordToNumbers(String word) {
		assertThatIsWord(word);
		return encript(word, false);
	}

	public String cryptSentence(String sentence) {
		return encript(sentence, true);
	}

	private void assertThatIsWord(String word) {
		if (wordContainsSpace(word)) {
			throw new InvalidParameterException();
		}
	}

	private boolean wordContainsSpace(String word) {
		return word.contains(" ");
	}

	private String encript(String word, boolean isChar) {
		char[] wordArray = word.toCharArray();
		String newWord = "";
		for (int i = 0; i < word.length(); i++) {
			int charValue = wordArray[i];
			newWord += buildNewWord(isChar, charValue);
		}
		return newWord;
	}

	private String buildNewWord(boolean isChar, int charValue) {
		if (isChar) {
			return castStringFromChar(charValue);
		} else {
			return castStringFromInt(charValue);
		}
	}

	private String castStringFromChar(int charValue) {
		return String.valueOf((char) (charValue + 2));
	}

	private String castStringFromInt(int charValue) {
		return String.valueOf(charValue + 2);
	}

	public String cryptWord(String word, String charsToReplace) {
		if (wordContainsSpace(word))
			throw new InvalidParameterException();

		char[] wordArray = word.toCharArray();
		char[] replacement = charsToReplace.toCharArray();
		char[] result = wordArray.clone();
		for (int i = 0; i < wordArray.length; i++) {
			for (int j = 0; j < replacement.length; j++) {
				if (replacement[j] == wordArray[i]) {
					int charValue = wordArray[i];
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
