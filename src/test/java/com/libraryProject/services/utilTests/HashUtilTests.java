package com.libraryProject.services.utilTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.libraryProject.services.util.HashUtil;

@SpringBootTest
public class HashUtilTests {
	
	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("123");
		System.out.println(hash);
		assertThat(hash.length()).isEqualTo(64);
	}
}
