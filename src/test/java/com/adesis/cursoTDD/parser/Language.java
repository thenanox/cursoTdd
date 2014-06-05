package com.adesis.cursoTDD.parser;

public interface Language {

	public String deletePluralWord(Parser parser, String query);

	public String deleteExcludedWords(Parser parser, String query);

}
