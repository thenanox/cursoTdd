package com.adesis.cursoTDD.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

	private static final String[] EXCLUSIONS_LIST = {"un ","uno ","una ","unos ","unas ","el ","la ","los ","las "};

	public List<String> parse(String query) {
		List<String> results = new ArrayList<String>();
		for(String exclusion: EXCLUSIONS_LIST){
			if(query.contains(exclusion)){
				query = query.replace(exclusion, "");
			}
		}
		query = query.toUpperCase();
		if(query.endsWith("S")){
			query = query.substring(0, query.length() - 1);
		}
		results.add(query);
		return results;
	}

}
