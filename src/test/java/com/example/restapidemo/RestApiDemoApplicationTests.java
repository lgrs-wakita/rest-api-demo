package com.example.restapidemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class RestApiDemoApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void makePassword() {
		log.debug("{}", passwordEncoder.encode("password"));
	}

}
