package com.adesis.cursoTDD.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

	private static final String[] EXCLUSIONS_LIST = { "un ", "uno ", "una ", "unos ", "unas ",
			"el ", "la ", "los ", "las " };

	private static final String[] SIGNS_EXCLUDED = { ".", ",", ";", "(", ")", "/" };

	public List<String> parse(String query) {
		List<String> results = new ArrayList<String>();
		query = deleteExcludedWords(query).toUpperCase();
		query = deleteSigns(query);
		query = deletePluralWord(query);
		results.add(query);

		return results;
	}

	private String deleteSigns(String query) {
		for (String sign : SIGNS_EXCLUDED) {
			if (query.contains(sign)) {
				query = query.replace(sign, "");
			}
		}

		return query;
	}

	private String deletePluralWord(String query) {
		if (query.endsWith("S")) {
			query = query.substring(0, query.length() - 1);
		}
		return query;
	}

	private String deleteExcludedWords(String query) {
		for (String exclusion : EXCLUSIONS_LIST) {
			if (query.contains(exclusion)) {
				query = query.replace(exclusion, "");
			}
		}
		return query;
	}
}
