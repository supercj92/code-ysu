package com.cfysu.algorithm;

public class SimpleSort {
	
	public static void bubbleSort(int[] sortArray){
		for(int out = sortArray.length - 1;out > 0;out--){
			for(int in = 0;in < out;in++){
				//System.out.println(sortArray[in] + ":" + sortArray[in + 1]);
				if(sortArray[in] > sortArray[in + 1]){
					swap(in, in + 1, sortArray);
				}
			}
		}
	}
	
	public static void selectSort(int[] sortArray){
		for(int out = 0;out < sortArray.length -1;out ++){
			int max = out;
			for(int in = out + 1;in < sortArray.length;in++){
				if(sortArray[in] > sortArray[max]){
					max = in;
				}
			}
			swap(out, max, sortArray);
		}
	}
	
	public static void insertSort(int[] sortArray){
		
	}
	
	private static void swap(int a, int b, int[] sortArray){
		int temp = sortArray[a];
		sortArray[a] = sortArray[b];
		sortArray[b] = temp;
	}

}
