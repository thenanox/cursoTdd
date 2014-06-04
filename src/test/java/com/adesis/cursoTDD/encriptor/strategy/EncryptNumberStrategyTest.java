package com.adesis.cursoTDD.encriptor.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EncryptNumberStrategyTest {

	private EncryptNumberStrategy numberStrategy = new EncryptNumberStrategy();

	@Test
	public void crypt() {
		assertThat(numberStrategy.crypt(100)).isEqualTo("102");
	}

}
