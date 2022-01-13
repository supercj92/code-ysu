package com.cfysu.lab.spring.mvc;

import java.util.Map;

import org.assertj.core.api.AbstractMapAssert;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebMvcTest(controllers = ControllerMain.class)
class ControllerMainTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private TestRestTemplate testRestTemplate;
	//private TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Test
	public void testFoo(){
		String res = testRestTemplate.getForObject("/foo", String.class);
		assertThat(res).isEqualTo("ok");
	}

	@Test
	public void testBar(){
		Map<String, String> forObject = testRestTemplate.getForObject("/bar", Map.class);
		assertThat(forObject).containsKey("key");
	}

	@Test
	void contextLoads() {
		System.out.println();
	}
}
