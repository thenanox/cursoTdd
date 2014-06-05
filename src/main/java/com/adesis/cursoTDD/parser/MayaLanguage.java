package com.adesis.cursoTDD.parser;

public class MayaLanguage implements Language {

	private static final String[] EXCLUSIONS_LIST = { "!!!" };

	public String obtainPluralCharacter() {
		return "?";
	}

	public String[] obtainArticles() {
		return EXCLUSIONS_LIST;
	}

	@Override
	public String deletePluralWord(Parser parser, String query) {
		if (query.startsWith(obtainPluralCharacter())) {
			query = query.substring(1);
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
