package com.adesis.cursoTDD.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParserTest {

	@Test
	public void transform_word_to_uppercase() {
		List<String> results = parse("cocinero");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}

	@Test
	public void eliminar_articulo_de_query() {
		List<String> results = parse("un cocinero");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}

	private static final String[] EXCLUSIONS_LIST = {"un ","uno ","una ","unos ","unas ","el ","la ","los ","las "};
	
	public List<String> parse(String query) {
		List<String> results = new ArrayList<String>();
		for(String exclusion: EXCLUSIONS_LIST){
			if(query.contains(exclusion)){
				query = query.replace(exclusion, "");
			}
		}
		results.add(query.toUpperCase());
		return results;
	}

}
