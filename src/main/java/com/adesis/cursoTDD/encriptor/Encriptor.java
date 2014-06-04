package com.adesis.cursoTDD.encriptor;

import com.adesis.cursoTDD.encriptor.strategy.EncryptStrategy;
import com.adesis.cursoTDD.encriptor.validator.WordValidator;

public class Encriptor {

	private WordValidator validator;
	private EncryptStrategy strategy;

	public void setValidator(WordValidator validator) {
		this.validator = validator;
	}

	public void setStrategy(EncryptStrategy strategy) {
		this.strategy = strategy;
	}

	public String cryptWord(String word) {
		validator.assertThatIsWord(word);
		return encrypt(word);
	}

	public String cryptSentence(String sentence) {
		return encrypt(sentence);
	}

	private String encrypt(String word) {
		String newWord = "";
		for (int i = 0; i < word.length(); i++) {
			newWord += strategy.crypt(word.charAt(i));
		}
		return newWord;
	}

	public String cryptWord(String word, String charsToReplace) {
		validator.assertThatIsWord(word);
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
