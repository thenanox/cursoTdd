package com.adesis.cursoTDD.encriptor.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EncryptCharStrategyTest {
	private EncryptCharStrategy charStrategy = new EncryptCharStrategy();

	@Test
	public void crypt() {
		assertThat(charStrategy.crypt(100)).isEqualTo("f");
	}
}
