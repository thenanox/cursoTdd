package com.adesis.cursoTDD.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {
	private Parser parser;
	private EuskeraLanguage euskeraLanguage = new EuskeraLanguage();
	private MayaLanguage mayaLanguage = new MayaLanguage();

	@Before
	public void setup() {
		parser = new Parser(new SpanishLanguage());
	}

	@Test
	public void transform_word_to_uppercase() {
		List<String> results = parser.parse("cocinero");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_articulo_con_espacio_por_delante() {
		List<String> results = parser.parse("un cocinero");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_articulo_con_espacio_por_detras() {
		List<String> results = parser.parse("cocinero un");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_plural_de_query() {
		List<String> results = parser.parse("cocineros");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_signos_de_puntuacion_de_query() {
		List<String> results = parser.parse("cocinero.");
		assertThat(results).contains("COCINERO");
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
	public void eliminar_articulo_de_query_en_euskera() {
		changeLanguageTo(euskeraLanguage);
		List<String> results = parser.parse("kkk cocinero");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_plural_de_query_en_euskera() {
		changeLanguageTo(euskeraLanguage);
		List<String> results = parser.parse("cocinerok");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_articulo_de_query_en_maya() {
		changeLanguageTo(mayaLanguage);
		List<String> results = parser.parse("!!! cocinero");
		assertThat(results).contains("COCINERO");
	}

	@Test
	public void eliminar_plural_de_query_en_maya() {
		changeLanguageTo(mayaLanguage);
		List<String> results = parser.parse("?cocinero");
		assertThat(results).contains("COCINERO");
	}

	private void changeLanguageTo(Language language) {
		parser.setLanguage(language);
	}

}
