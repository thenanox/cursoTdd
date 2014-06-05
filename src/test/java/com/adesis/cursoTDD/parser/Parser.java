package com.adesis.cursoTDD.parser;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

	private static final String[] EXCLUSIONS_LIST = { "un ", "uno ", "una ", "unos ", "unas ",
			"el ", "la ", "los ", "las " };

	private static final String[] SIGNS_EXCLUDED = { ".", ",", ";", "(", ")", "/" };

	public List<String> parse(String query) {
		List<String> results = new ArrayList<String>();
		query = deleteExcludedWords(query).toUpperCase();
		for (String word : parseWords(query)) {
			word = deleteSigns(word);
			word = modifyAccentsAndSpecialCharacters(word);
			word = deletePluralWord(word);
			results.add(word);
		}

		return removeDuplicatedWords(results);
	}

	private String deleteExcludedWords(String query) {
		for (String exclusion : EXCLUSIONS_LIST) {
			if (query.contains(exclusion)) {
				query = query.replace(exclusion, "");
			}
		}
		return query;
	}
	
	private List<String> parseWords(String query) {
		return Arrays.asList(query.split(" "));
	}
	
	private String deleteSigns(String word) {
		for (String sign : SIGNS_EXCLUDED) {
			if (word.contains(sign)) {
				word = word.replace(sign, "");
			}
		}

		return word;
	}
	
	private String modifyAccentsAndSpecialCharacters(String word) {
		return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private String deletePluralWord(String query) {
		if (query.endsWith("S")) {
			query = query.substring(0, query.length() - 1);
		}
		return query;
	}

	private List<String> removeDuplicatedWords(List<String> query) {
		Set<String> words = new HashSet<String>();
		for (String word : query) {
			words.add(word);
		}
		return new ArrayList<String>(words);
	}
}
