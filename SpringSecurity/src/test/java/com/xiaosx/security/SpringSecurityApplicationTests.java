package com.xiaosx.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testEncode(){
		String encode = new BCryptPasswordEncoder().encode("xiaoshux");
		System.out.println("encode = " + encode);
	}

}
