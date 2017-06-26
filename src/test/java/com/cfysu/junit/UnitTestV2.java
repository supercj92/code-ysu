package com.cfysu.junit;

import org.junit.Test;

import com.cfysu.model.Ford;

import java.util.Random;

public class UnitTestV2 {

	private Random random = new Random();
	@Test
	public void testConstruct(){
		new Ford();
	}

	@Test
	public void testDivid(){
		System.out.println(10/3);
	}

	@Test
	public void testVerificationCode(){
		int verficationCode = random.nextInt(9999) % (9999-1000+1) + 1000;
		System.out.print(verficationCode);
	}

	@Test
	public void testGetName(){
		System.out.print(UnitTest.class.getName());
		ClassLoader clazz = UnitTest.class.getClassLoader();
	}

}
