package com.adesis.cursoTDD.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class ParserTest {
    private Parser parser = new Parser();
    
	@Test
	public void transform_word_to_uppercase() {
		List<String> results = parser.parse("cocinero");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}

	@Test
	public void eliminar_articulo_de_query() {
		List<String> results = parser.parse("un cocinero");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}
	
	@Test
	public void eliminar_plural_de_query() {
		List<String> results = parser.parse("cocineros");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}
	
	@Test
	public void eliminar_signos_de_puntuacion_de_query() {
		List<String> results = parser.parse("cocineros.");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}

}
