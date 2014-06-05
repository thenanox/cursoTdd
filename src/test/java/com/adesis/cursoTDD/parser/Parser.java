package com.adesis.cursoTDD.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

	private Language language;

	public Parser(Language language) {
		this.setLanguage(language);
	}

	private static final String[] SIGNS_EXCLUDED = { ".", ",", ";", "(", ")", "/" };

	public List<String> parse(String query) {
		List<String> results = new ArrayList<String>();
		query = language.deleteExcludedWords(this, query).toUpperCase();
		for (String word : splitBySpace(query)) {
			word = deleteSigns(word);
			word = language.deletePluralWord(this, word);
			results.add(word);
		}

		return removeDuplicatedWords(results);
	}

	private String deleteSigns(String query) {
		for (String sign : SIGNS_EXCLUDED) {
			if (query.contains(sign)) {
				query = query.replace(sign, "");
			}
		}

		return query;
	}

	private List<String> splitBySpace(String query) {
		return Arrays.asList(query.split(" "));

	}

	private List<String> removeDuplicatedWords(List<String> query) {
		Set<String> words = new HashSet<String>();
		for (String word : query) {
			words.add(word);
		}

		return new ArrayList<String>(words);

	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
