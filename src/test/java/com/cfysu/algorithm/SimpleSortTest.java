package com.cfysu.algorithm;

import java.util.Arrays;

import org.junit.Test;

public class SimpleSortTest {

	private int[] intArray = {3, 44, 99, 44, 22, 4, 0, 99};
	@Test
	public void testBubbleSort(){
		System.out.println(Arrays.toString(intArray));
		SimpleSort.bubbleSort(intArray);
		System.out.println(Arrays.toString(intArray));
	}
	
	@Test
	public void testSelectSort(){
		System.out.println(Arrays.toString(intArray));
		SimpleSort.selectSort(intArray);
		System.out.println(Arrays.toString(intArray));
	}

	@Test
	public void testInsertSort(){
		System.out.println(Arrays.toString(intArray));
		SimpleSort.insertSort(intArray);
		System.out.println(Arrays.toString(intArray));
	}
}
