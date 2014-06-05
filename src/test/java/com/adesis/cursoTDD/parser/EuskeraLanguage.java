package com.adesis.cursoTDD.parser;

public class EuskeraLanguage implements Language {

	private static final String[] EXCLUSIONS_LIST = { "kkk", "ppp", "qqq" };

	public String obtainPluralCharacter() {
		return "K";
	}

	public String[] obtainArticles() {
		return EXCLUSIONS_LIST;
	}

	@Override
	public String deletePluralWord(Parser parser, String query) {
		if (query.endsWith(obtainPluralCharacter())) {
			query = query.substring(0, query.length() - 1);
		}
		return query;
	}

	@Override
	public String deleteExcludedWords(Parser parser, String query) {
		for (String exclusion : obtainArticles()) {
			if (query.contains(exclusion)) {
				query = query.replace(exclusion + " ", "").replace(" " + exclusion, "");
			}
		}
		return query;
	};
}
