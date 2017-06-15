package com.cfysu.junit;

import org.junit.Test;

import com.cfysu.model.Ford;

public class UnitTestV2 {
	@Test
	public void testConstruct(){
		new Ford();
	}

	@Test
	public void testDivid(){
		System.out.println(10/3);
	}

}
