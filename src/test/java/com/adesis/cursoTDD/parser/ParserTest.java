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
		List<String> results = parser.parse("cocinero.");
		assertThat(results.get(0)).isEqualTo("COCINERO");
	}

	@Test
	public void split_por_espacios() {
		List<String> results = parser.parse("cocinero torero");
		assertThat(results).contains("COCINERO", "TORERO");
	}

	@Test
	public void elimina_duplicados() {
		List<String> results = parser.parse("cocinero cocinero torero");
		assertThat(results).contains("COCINERO", "TORERO");
	}
	
	@Test
	public void modifica_tildes_y_caracteres() {
		List<String> results = parser.parse("Arguíñáno");
		assertThat(results).contains("ARGUINANO");
	}

}
